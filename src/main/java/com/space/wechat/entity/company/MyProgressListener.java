package com.space.wechat.entity.company;

import java.text.DecimalFormat;

import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.ProgressListener;

public class MyProgressListener implements ProgressListener {

	private HttpSession session;

	public MyProgressListener() {

	}

	public MyProgressListener(HttpSession session) {
		this.session = session;
		ProgressEntity ps = new ProgressEntity();
		session.setAttribute("upload_ps", ps);
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	@Override
	public void update(long pBytesRead, long pContentLength, int pItems) {
		try {
			ProgressEntity ps = (ProgressEntity) session
					.getAttribute("upload_ps");
			long processTime = 1000l;
			if (ps.getProcessStartTime() == 0l) {
				processTime = 1000l;
			} else {
				processTime = System.currentTimeMillis()
						- ps.getProcessStartTime();
			}
			if (processTime < 1000l) {
				processTime = 1000l;
			}
			Double pMin = (double) (pBytesRead - ps.getpBytesRead())
					/ (double) (processTime / 1000);
			System.out.println("pMin123" + (pBytesRead - ps.getpBytesRead())
					+ " pBytesRead:" + pBytesRead + "ps.getpBytesRead()"
					+ ps.getpBytesRead());

			if (pMin != 0d) {
				DecimalFormat df = new DecimalFormat("0.0");
				ps.setpMin(Double.parseDouble(df.format(pMin)));
			}
			Double leftMin = (((pContentLength - pBytesRead) / 1024) / ps
					.getpMin()) / 60;
			if (leftMin != 0d) {
				DecimalFormat df = new DecimalFormat("0.0");
				ps.setpLeftMin(Double.parseDouble(df.format(leftMin)));
			}

			ps.setpBytesRead(pBytesRead);
			ps.setpContentLength(pContentLength);
			ps.setpItems(pItems);
			if (ps.getProcessStartTime() == 0l) {
				ps.setProcessStartTime(System.currentTimeMillis());
			}
			// 本轮上传多少容量

			// 更新
			session.setAttribute("upload_ps", ps);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}