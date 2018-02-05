<template>
  <div class="datetime-picker" :style="{ width: width }">
    <input
      type="text"
      :style="styleObj"
      :readonly="readonly"
      :value="value"
      @click="show = !show">
    <div class="picker-wrap" v-show="show">
      <table class="date-picker">
        <thead>
          <tr class="date-head">
            <th colspan="4">
              <span class="btn-prev" @click="yearClick(-1)">&lt;</span>
              <span class="show-year">{{now.getFullYear()}}</span>
              <span class="btn-next" @click="yearClick(1)">&gt;</span>
            </th>
            <th colspan="3">
              <span class="btn-prev" @click="monthClick(-1)">&lt;</span>
              <span class="show-month">{{months[now.getMonth()]}}</span>
              <span class="btn-next" @click="monthClick(1)">&gt;</span>
            </th>
          </tr>
          <tr class="date-days">
            <th v-for="day in days">{{day}}</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="i in 6">
            <td v-for="j in 7"
              :class="date[i * 7 + j] && date[i * 7 + j].status"
              :date="date[i * 7 + j] && date[i * 7 + j].date"
              @click="pickDate(i * 7 + j)">{{date[i * 7 + j] && date[i * 7 + j].text}}</td>
          </tr>
        </tbody>
      </table>

      <div v-show="showtime" class="picker-time">
        <select @change="changeTime" v-model="timeHour">
            <option value="00">24时</option>
            <option value="01">01时</option>
            <option value="02">02时</option>
            <option value="03">03时</option>
            <option value="04">04时</option>
            <option value="05">05时</option>
            <option value="06">06时</option>
            <option value="07">07时</option>
            <option value="08">08时</option>
            <option value="09">09时</option>
            <option value="10">10时</option>
            <option value="11">11时</option>
            <option value="12">12时</option>
            <option value="13">13时</option>
            <option value="14">14时</option>
            <option value="15">15时</option>
            <option value="16">16时</option>
            <option value="17">17时</option>
            <option value="18">18时</option>
            <option value="19">19时</option>
            <option value="20">20时</option>
            <option value="21">21时</option>
            <option value="22">22时</option>
            <option value="23">23时</option>
        </select>

        <select @change="changeTime" v-model="timeMinute">
            <option value="00">00分</option>
            <option value="05">05分</option>
            <option value="10">10分</option>
            <option value="15">15分</option>
            <option value="20">20分</option>
            <option value="25">25分</option>
            <option value="30">30分</option>
            <option value="35">35分</option>
            <option value="40">40分</option>
            <option value="45">45分</option>
            <option value="50">50分</option>
            <option value="55">55分</option>
        </select>
        <div @click="pickTime">确定</div>
    </div>
    </div>

  </div>
</template>

<script>
  export default {
    props: {
      width: { type: String, default: '288px' },
      readonly: { type: Boolean, default: false },
      value: { type: String, default: '' },
      format: { type: String, default: 'YYYY-MM-DD' },
      showtime: false,
      timeHour: { type: String, default: '12' },
      timeMinute: { type: String, default: '00' }
    },
    data () {
      return {
        show: false,
        days: ['日', '一', '二', '三', '四', '五', '六'],
        months: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一', '十二'],
        date: [],
        now: new Date()
      }
    },
    watch: {
      now () {
        this.update()
      },
      show () {
        this.update()
        let datestr = this.value.split(' ')
        if (this.value === '') {
          return
        }
        if (this.showtime) {
          this.timeHour = datestr[1].split(':')[0]
          let minute = datestr[1].split(':')[1]
          this.timeMinute = 5 * parseInt(minute / 5)
        }
        let date = datestr[0]
        let day = parseInt(date.split('-')[2])
        for (let i = 0; i < this.date.length; i++) {
          if (this.date[i].text === day) {
            this.date[i].status = 'date-active'
            break
          }
        }
      }
    },
    methods: {
      close () {
        this.show = false
      },
      update () {
        var arr = []
        var time = new Date(this.now)
        time.setMonth(time.getMonth(), 1)       // the first day
        var curFirstDay = time.getDay()
        curFirstDay === 0 && (curFirstDay = 7)
        time.setDate(0)               // the last day
        var lastDayCount = time.getDate()
        for (let i = curFirstDay; i > 0; i--) {
          arr.push({
            text: lastDayCount - i + 1,
            time: new Date(time.getFullYear(), time.getMonth(), lastDayCount - i + 1),
            status: 'date-pass'
          })
        }

        time.setMonth(time.getMonth() + 2, 0)     // the last day of this month
        var curDayCount = time.getDate()
        time.setDate(1)               // fix bug when month change
        var value = this.value || this.stringify(new Date())
        for (let i = 0; i < curDayCount; i++) {
          let tmpTime = new Date(time.getFullYear(), time.getMonth(), i + 1)
          let status = ''
          this.stringify(tmpTime) === value && (status = 'date-active')
          arr.push({
            text: i + 1,
            time: tmpTime,
            status: status
          })
        }

        var j = 1
        while (arr.length < 42) {
          arr.push({
            text: j,
            time: new Date(time.getFullYear(), time.getMonth() + 1, j),
            status: 'date-future'
          })
          j++
        }
        this.date = arr
      },
      yearClick (flag) {
        this.now.setFullYear(this.now.getFullYear() + flag)
        this.now = new Date(this.now)
      },
      monthClick (flag) {
        this.now.setMonth(this.now.getMonth() + flag)
        this.now = new Date(this.now)
      },
      changeTime () {
        if (this.showtime) {
          this.value = this.stringify() + ' ' + this.timeHour + ':' + this.timeMinute
        }
      },
      pickDate (index) {
        if (!this.showtime) {
          this.show = false
        }
        this.now = new Date(this.date[index].time)
        this._timeout = setTimeout(
        () => { this.date[index].status = 'date-active' }, 100
        )
        this.value = this.stringify()
        if (this.showtime) {
          if (this.timeMinute === '0' || this.timeMinute === 0) {
            this.timeMinute = '00'
          }
          this.value = this.value + ' ' + this.timeHour + ':' + this.timeMinute + ':00'
        }
      },
      pickTime (index) {
        this.value = this.stringify()
        this.show = false
        if (this.timeMinute === '0' || this.timeMinute === 0) {
          this.timeMinute = '00'
        }
        this.value = this.value + ' ' + this.timeHour + ':' + this.timeMinute + ':00'
      },
      parse (str) {
        var time = new Date(str)
        return isNaN(time.getTime()) ? null : time
      },
      stringify (time = this.now, format = this.format) {
        var year = time.getFullYear()
        var month = time.getMonth() + 1
        if (month < 10) {
          month = '0' + month
        }

        var date = time.getDate()

        if (date < 10) {
          date = '0' + date
        }

        var monthName = this.months[time.getMonth()]

        var map = {
          YYYY: year,
          MMM: monthName,
          MM: ('0' + month).slice(-2),
          M: month,
          DD: ('0' + date).slice(-2),
          D: date
        }
        return format.replace(/Y+|M+|D+/g, function (str) {
          return map[str]
        })
      }
    },
    ready () {
      let datestr = this.value.split(' ')
      this.now = this.parse(datestr[0]) || new Date()
      document.addEventListener('click', (e) => {
        if (this.$el !== null && !this.$el.contains(e.target)) {
          this.close()
        }
      }, false)
    },
    beforeDestroy () {
      document.removeEventListener('click', this.close, false)
    }
  }
</script>

<style scoped>
.datetime-picker {
  position: relative;
  display: inline-block;
  font-family: "Segoe UI","Lucida Grande",Helvetica,Arial,"Microsoft YaHei";
  -webkit-font-smoothing: antialiased;
  color: #333;
}

.datetime-picker * {
  box-sizing: border-box;
}

.datetime-picker input {
    font-size: 13px;
    padding: 6px 8px;
    width: 100%;
    margin: 1px 0;
    color: #394263;
    border: 1px solid #dbe1e8;
    line-height: 1.42857143;
    color: #555;
    background-color: #fff;
    background-image: none;
    border-radius: 4px;
}

.datetime-picker .picker-wrap {
  position: absolute;
  z-index: 2000;
  width: 238px;
  margin-top: 2px;
  background-color: #fff;
  box-shadow: 0 0 6px #ccc;
}

.datetime-picker .showtime {
  height: 320px;
}

.datetime-picker .notshowtime {
  height: 280px;
}

.picker-time {
    height: 50px;
    display: flex;
    align-items: center;
    border-top: 1px solid #ddd;
    justify-content: space-around;
}

.picker-time div{
    padding: 2px 15px;
    border: 1px solid #1bbae1;
    border-radius: 4px;
    background-color: #3bb4f2;
    color: #fff;
    cursor: pointer;
}

.picker-time select {
    width: 70px;
    font-size: 13px;
    padding: 6px 8px;
    max-width: 100%;
    margin: 1px 0;
    color: #394263;
    border-color: #dbe1e8;
}
.datetime-picker table {
  width: 100%;
  border-collapse: collapse;
  border-spacing: 0;
  text-align: center;
  font-size: 13px;
}

.datetime-picker tr {
  height: 34px;
  border: 0 none;
}

.datetime-picker th, .datetime-picker td {
  user-select: none;
  width: 34px;
  height: 34px;
  padding: 0;
  border: 0 none;
  line-height: 34px;
  text-align: center;
}

.datetime-picker td {
  cursor: pointer;
}

.datetime-picker td:hover {
  background-color: #f0f0f0;
}

.datetime-picker td.date-pass, .datetime-picker td.date-future {
  color: #aaa;
}

.datetime-picker td.date-active {
  background-color: #ececec;
  color: #3bb4f2;
}

.datetime-picker .date-head {
  background-color: #3bb4f2;
  text-align: center;
  color: #fff;
  font-size: 14px;
}

.datetime-picker .date-days {
  color: #3bb4f2;
  font-size: 14px;
}

.datetime-picker .show-year {
  display: inline-block;
  min-width: 62px;
  vertical-align: middle;
}

.datetime-picker .show-month {
  display: inline-block;
  min-width: 28px;
  vertical-align: middle;
}

.datetime-picker .btn-prev,
.datetime-picker .btn-next {
  cursor: pointer;
  display: inline-block;
  padding: 0 10px;
  vertical-align: middle;
}

.datetime-picker .btn-prev:hover,
.datetime-picker .btn-next:hover {
  background: rgba(16, 160, 234, 0.5);
}
</style>
