package com.space.wechat.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FfmpegUtil {
	private static final String FFMPEGPATH = "ffmpeg";
	public static final String TEMPPATH = System.getProperties().getProperty("java.io.tmpdir")
			+ System.getProperties().getProperty("file.separator");
	private static final Integer HDAUDIOFREQ = 44100;
	private static final Integer SDAUDIOFREQ = 22050;
	private static final Integer HDAUDIOBIT = 128;
	private static final Integer SDAUDIOBIT = 96;
	private static final Integer HDVIDEOHEIGHT = 720;
	private static final Integer SDVIDEOHEIGHT = 360;
	private static final Integer HDVIDEOWIDTH = 1280;
	private static final Integer SDVIDEOWIDTH = 640;
	private static final Integer HDVIDEOBIT = 1000;
	private static final Integer SDVIDEOBIT = 768;

	public static String makeImgbyvideo(String videofilepath, String imgfilepath) {
		System.out.println(videofilepath + "->" + TEMPPATH + imgfilepath + ".bmp");
		List<String> commend = new java.util.ArrayList<String>();
		commend.add(FFMPEGPATH);
		commend.add("-i");
		commend.add(videofilepath);
		commend.add("-y");
		commend.add("-f");
		commend.add("image2");
		commend.add("-ss");
		commend.add("00:00:03");
		commend.add("-t");
		commend.add("00:00:01");
		commend.add("-s");
		commend.add("320x240");
		commend.add(TEMPPATH + imgfilepath + ".bmp");
		try {
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(commend);
			builder.redirectErrorStream(true);
			Process p = builder.start();
			String line = null;
			BufferedReader buf = null;
			buf = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = buf.readLine()) != null) {
				continue;
			}
			int ret = p.waitFor();
			return TEMPPATH + imgfilepath + ".bmp";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static boolean encodeToFlvVideo(String videosrc, String videotar) {
		System.out.println(videosrc + "->" + videotar);
		try {
			Map baseinfo = translateInfo(getMovieInfo(videosrc));
			encode(getFlvInfoByBaseInfo(baseinfo), videotar);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static String encodeToMp4Video(String videosrc, String videotar) {
		System.out.println(videosrc + "->" + videotar);
		try {
			Map<String, String> baseinfo = FfmpegUtil.translateInfo(FfmpegUtil.getMovieInfo(videosrc));
			if (baseinfo.get("videoEncode").contains("h264")) {
				return videosrc;
			}
			return encode(getMp4InfoByBaseInfo(baseinfo), videotar);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getVedioTime(String videosrc) {
		return translateInfo(getMovieInfo(videosrc)).get("duration");
	}

	/**
	 * 通过ffmpeg获取影片的信息
	 * 
	 * @param movieUrl
	 *            <a href="http://my.oschina.net/u/556800" class="referer"
	 *            target="_blank">@return</a>
	 */
	private static Map<String, String> getMovieInfo(String movieFfmpegUrl) {
		try {
			List<String> command = new java.util.ArrayList<String>();
			command.add("ffmpeg");
			command.add("-i");
			command.add(movieFfmpegUrl);
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(command);
			builder.redirectErrorStream(true);
			Process p = builder.start();
			BufferedReader buf = null;
			String line = null;
			buf = new BufferedReader(new InputStreamReader(p.getInputStream()));

			Map<String, String> info = new HashMap<String, String>();
			info.put("filepath", movieFfmpegUrl);
			while ((line = buf.readLine()) != null) {
				// System.out.println(line);
				if (line.contains("Duration:")) {
					info.put("Duration", line.substring(line.indexOf("Duration:") + 9));
				} else if (line.contains("Video:")) {
					info.put("Video", line.substring(line.indexOf("Video:") + 6));
				} else if (line.contains("Audio:")) {
					info.put("Audio", line.substring(line.indexOf("Audio:") + 6));
				}
				continue;
			}
			int ret = p.waitFor();
			return info;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	private static Map<String, String> translateInfo(Map<String, String> info) {
		String duration = info.get("Duration").split(",")[0];
		String audio = info.get("Audio");
		String audiofreq = "";
		String audiobit = "";
		String video = info.get("Video");
		String videobit = "";
		String videoheight = "";
		String videowidth = "";
		String videoEncode = "";
		String videotbn = null;
		for (String subinfo : audio.split(",")) {
			if (subinfo.contains("Hz")) {
				audiofreq = subinfo.substring(0, subinfo.indexOf("Hz")).trim();
			} else if (subinfo.contains("kb/s")) {
				audiobit = subinfo.substring(0, subinfo.indexOf("kb/s")).trim();
			}
		}
		String[] videoInfos = video.split(",");
		videoEncode = videoInfos[0];
		for (int i = 1; i < videoInfos.length; i++) {
			String subinfo = videoInfos[i];
			if (subinfo.contains("x")) {
				videowidth = subinfo.split("x")[0].trim();
				videoheight = subinfo.split("x")[1].trim();
			} else if (subinfo.contains("kb/s")) {
				videobit = subinfo.substring(0, subinfo.indexOf("kb/s")).trim();
			} else if (subinfo.contains("tbn")) {
				videotbn = subinfo.substring(0, subinfo.indexOf("tbn")).trim();
			}
		}
		Map<String, String> finalInfo = new HashMap<String, String>();
		finalInfo.put("duration", duration);
		finalInfo.put("videoEncode", videoEncode);
		finalInfo.put("audiofreq", StringUtil.getQuantity(audiofreq));
		finalInfo.put("audiobit", StringUtil.getQuantity(audiobit));
		finalInfo.put("videobit", StringUtil.getQuantity(videobit));
		finalInfo.put("videoheight", StringUtil.getQuantity(videoheight));
		finalInfo.put("videowidth", StringUtil.getQuantity(videowidth));
		finalInfo.put("filepath", info.get("filepath"));
		return finalInfo;
	}

	private static Map<String, String> getFlvInfoByBaseInfo(Map<String, String> info) {
		Map<String, String> HDInfo = new HashMap(info);
		HDInfo.put("codec", "libx264");
		HDInfo.put("quality", ".HD.flv");
		if (StringUtil.isNullOrEmpty(info.get("audiofreq")) || Integer.parseInt(info.get("audiofreq")) > HDAUDIOFREQ) {
			HDInfo.put("audiofreq", HDAUDIOFREQ.toString());
		}
		if (StringUtil.isNullOrEmpty(info.get("audiobit")) || Integer.parseInt(info.get("audiobit")) > HDAUDIOBIT) {
			HDInfo.put("audiobit", HDAUDIOBIT.toString());
		}
		if (StringUtil.isNullOrEmpty(info.get("videobit")) || Integer.parseInt(info.get("videobit")) > HDVIDEOBIT) {
			HDInfo.put("videobit", HDVIDEOBIT.toString());
		}
		if (StringUtil.isNotEmpty(info.get("videoheight"))) {
			int height = Integer.parseInt(info.get("videoheight"));
			int width = Integer.parseInt(info.get("videowidth"));
			if (height > width) { // 竖屏
				if (height > HDVIDEOWIDTH) {
					HDInfo.put("videoheight", HDVIDEOWIDTH.toString());
				}
				if (width > HDVIDEOHEIGHT) {
					HDInfo.put("videowidth", HDVIDEOHEIGHT.toString());
				}
			} else {
				if (height > HDVIDEOHEIGHT) {
					HDInfo.put("videoheight", HDVIDEOHEIGHT.toString());
				}
				if (width > HDVIDEOWIDTH) {
					HDInfo.put("videowidth", HDVIDEOWIDTH.toString());
				}
			}
		} else {
			HDInfo.put("videoheight", HDVIDEOHEIGHT.toString());
			HDInfo.put("videowidth", HDVIDEOWIDTH.toString());
		}
		return HDInfo;
	}

	private static Map<String, String> getMp4InfoByBaseInfo(Map<String, String> info) {
		Map<String, String> HDInfo = new HashMap(info);
		HDInfo.put("codec", "libx264");
		HDInfo.put("quality", ".HD.mp4");
		if (StringUtil.isNullOrEmpty(info.get("audiofreq")) || Integer.parseInt(info.get("audiofreq")) > HDAUDIOFREQ) {
			HDInfo.put("audiofreq", HDAUDIOFREQ.toString());
		}
		if (StringUtil.isNullOrEmpty(info.get("audiobit")) || Integer.parseInt(info.get("audiobit")) > HDAUDIOBIT) {
			HDInfo.put("audiobit", HDAUDIOBIT.toString());
		}
		if (StringUtil.isNullOrEmpty(info.get("videobit")) || Integer.parseInt(info.get("videobit")) > HDVIDEOBIT) {
			HDInfo.put("videobit", HDVIDEOBIT.toString());
		}
		if (StringUtil.isNotEmpty(info.get("videoheight"))) {
			int height = Integer.parseInt(info.get("videoheight"));
			int width = Integer.parseInt(info.get("videowidth"));
			if (height > width) { // 竖屏
				if (height > HDVIDEOWIDTH) {
					HDInfo.put("videoheight", HDVIDEOWIDTH.toString());
				}
				if (width > HDVIDEOHEIGHT) {
					HDInfo.put("videowidth", HDVIDEOHEIGHT.toString());
				}
			} else {
				if (height > HDVIDEOHEIGHT) {
					HDInfo.put("videoheight", HDVIDEOHEIGHT.toString());
				}
				if (width > HDVIDEOWIDTH) {
					HDInfo.put("videowidth", HDVIDEOWIDTH.toString());
				}
			}
		} else {
			HDInfo.put("videoheight", HDVIDEOHEIGHT.toString());
			HDInfo.put("videowidth", HDVIDEOWIDTH.toString());
		}
		return HDInfo;
	}

	private static Map<String, String> getSDInfoByBaseInfo(Map<String, String> info) {
		Map<String, String> SDInfo = new HashMap(info);
		SDInfo.put("codec", "mpeg4");
		SDInfo.put("quality", ".SD.mp4");
		if (StringUtil.isNullOrEmpty(info.get("audiofreq")) || Integer.parseInt(info.get("audiofreq")) > SDAUDIOFREQ) {
			SDInfo.put("audiofreq", SDAUDIOFREQ.toString());
		}
		if (StringUtil.isNullOrEmpty(info.get("audiobit")) || Integer.parseInt(info.get("audiobit")) > SDAUDIOBIT) {
			SDInfo.put("audiobit", SDAUDIOBIT.toString());
		}
		if (StringUtil.isNullOrEmpty(info.get("videobit")) || Integer.parseInt(info.get("videobit")) > SDVIDEOBIT) {
			SDInfo.put("videobit", SDVIDEOBIT.toString());
		}
		if (StringUtil.isNotEmpty(info.get("videoheight"))) {
			int height = Integer.parseInt(info.get("videoheight"));
			int width = Integer.parseInt(info.get("videowidth"));
			if (height > width) { // 竖屏
				if (height > SDVIDEOWIDTH) {
					SDInfo.put("videoheight", SDVIDEOWIDTH.toString());
				}
				if (width > SDVIDEOHEIGHT) {
					SDInfo.put("videowidth", SDVIDEOHEIGHT.toString());
				}
			} else {
				if (height > SDVIDEOHEIGHT) {
					SDInfo.put("videoheight", SDVIDEOHEIGHT.toString());
				}
				if (width > SDVIDEOWIDTH) {
					SDInfo.put("videowidth", SDVIDEOWIDTH.toString());
				}
			}
		} else {
			SDInfo.put("videoheight", SDVIDEOHEIGHT.toString());
			SDInfo.put("videowidth", SDVIDEOWIDTH.toString());
		}
		return SDInfo;
	}

	private static String encode(Map<String, String> setting, String filepath) {
		String tarFilename = TEMPPATH + filepath + setting.get("quality");
		List<String> commend = new java.util.ArrayList<String>();
		commend.add(FFMPEGPATH);
		commend.add("-y");
		commend.add("-i");
		commend.add(setting.get("filepath"));
		if (!setting.get("quality").endsWith("flv")) {
			commend.add("-vcodec");
			commend.add(setting.get("codec"));
		}
		commend.add("-vb");
		commend.add(setting.get("videobit") + "k");
		commend.add("-s");
		commend.add(setting.get("videowidth") + "x" + setting.get("videoheight"));
		// commend.add("-acodec");
		// commend.add("libfaac");
		// commend.add("-ab");
		// commend.add(setting.get("audiobit") + "k");
		// commend.add("-ar");
		// commend.add(setting.get("audiofreq"));
		commend.add(tarFilename);

		try {
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(commend);
			System.out.println(commend);
			builder.redirectErrorStream(true);
			Process p = builder.start();
			String line = null;
			BufferedReader buf = null;
			buf = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = buf.readLine()) != null) {
				continue;
			}
			int ret = p.waitFor();
			System.out.println(FfmpegUtil.translateInfo(FfmpegUtil.getMovieInfo(tarFilename)));
			return tarFilename;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		// String filename =
		// "/media/kfq/C/Users/FeiQuan/Desktop/新建文件夹/prj3.mp4";
		// Map baseinfo = FfmpegUtil.translateInfo(FfmpegUtil
		// .getMovieInfo(filename));
		// System.out.println(baseinfo);
		// Map hdinfo = FfmpegUtil.getHDInfoByBaseInfo(baseinfo);
		// Map sdinfo = FfmpegUtil.getSDInfoByBaseInfo(baseinfo);
		// Date start = new Date();
		// // FfmpegUtil.encode(sdinfo, filename);
		// Date second = new Date();
		// System.out.println(second.getTime() - start.getTime());
		// FfmpegUtil.encode(hdinfo, filename);
		// System.out.println(new Date().getTime() - second.getTime());

	}
}
