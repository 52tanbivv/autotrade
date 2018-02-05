<template>
  <div>
    <canvas  :id="'canvas_' + uuid"></canvas>
    <p :class="'chart-info-' + chartType" :style='config&&config.info_style'>
      {{{legend}}}
    </p>
  </div>
</template>

<script>
import Chart from 'chart.js'

export default {
  props: {
    chartdata: {
      default: () => {
        return {}
      }
    },
    height: 160,
    width: 160
  },
  data () {
    return {
      legend: '',
      chart: {},
      uuid: Math.random().toString(36).substring(3, 18)
    }
  },
  computed: {
    canvas: function () {
      return this.$el.querySelector('canvas')
    },
    chartType: function () {
      return this.chartdata.type
    },
    options: function () {
      return this.chartdata.options ? this.chartdata.options : {showTooltips: false}
    },
    dataConfig: function () {
      let chartType = this.chartdata.type
      let isSegment = ['PolarArea', 'Pie', 'Doughnut'].indexOf(chartType) !== -1
      let dataConfig = this.chartdata.dataConfig
      // console.log('BEAFOR PROCSESS DATACONFIG=' + JSON.stringify(dataConfig, null, 2))
      if (isSegment) {
        let notAllowZero = ['Pie', 'Doughnut'].indexOf(chartType) !== -1
        for (let i = 0; i < dataConfig.length; i++) {
          let dateItem = dataConfig[i]
          if (dateItem.value === undefined || (notAllowZero && dateItem.value === 0)) {
            dateItem.value = notAllowZero ? 1 : 0
          }
        }
      }
      // else {
      //   for (let i = 0; i < dataConfig.length; i++) {
      //     let dataset = dataConfig[i]
      //     if (dataset.data === undefined) {
      //       dataset.data = []
      //     }
      //   }
      //   dataConfig = {
      //     labels: [],
      //     datasets: dataConfig
      //   }
      // }
      return dataConfig
    }
  },
  watch: {
    chartdata () {
      this.refresh()
    }
  },
  ready () {
    console.log('in ready chart' + this.chartdata.type + '\ndataConfig=')
    this.chart = {}
    let canvas = document.getElementById('canvas_' + this.uuid)
    let ctx = canvas.getContext('2d')
    canvas.width = this.width
    canvas.height = this.height
    this.chart = new Chart(ctx)[this.chartType](this.dataConfig, this.options)
    this.legend = this.chart.generateLegend()
    console.log('in ready legend=' + this.legend)
  },
  methods: {
    refresh () {
      console.log('in refresh chart' + this.chartdata.type)
      this.chart = {}
      // let ctx = this.canvas.getContext('2d')
      let canvas = document.getElementById('canvas_' + this.uuid)
      let ctx = canvas.getContext('2d')
      canvas.width = this.width
      canvas.height = this.height
      this.chart = new Chart(ctx)[this.chartType](this.dataConfig, this.options)
      this.legend = this.chart.generateLegend()
      console.log('in refresh legend=' + this.legend)
    }
  }
}
</script>

<style> 
  .chart-info-Pie {
    color: rgba(255, 255, 255, 0.3);
    position: absolute;
    top: 160px;
    right: 30px;
    width: 20%;
    display: block;
  }
  .chart-info-Bar {
    font-size: 15px;
    margin-left: auto;
  }
 .pie-legend {    
  list-style-type: none;
  -webkit-padding-start: 0;
  -moz-padding-start: 0;
    padding-left: 0;
  }

  .pie-legend-icon{
    display: block; 
    width: 15px;
    height: 15px;
    border-radius: 2px;
    display: inline-block;
    vertical-align:middle;
  }

  .pie-legend-text {
    padding-left: 7px;
    vertical-align:middle;
    color: #454545;
  }
  .pie-legend li {
    white-space: nowrap;
    position: relative;
    border-radius: 5px;
    padding: 2px 8px 2px 20px;
    font-size: smaller;
    cursor: default;
    padding-top: 2px;
    text-align: left;
  }
  .bar-legend, .chart-legend, .doughnut-legend, .line-legend, .polararea-legend, .radar-legend {
    list-style-type: none;
    text-align: center;
    -webkit-padding-start: 0;
    -moz-padding-start: 0;
    padding-left: 0;
  }
 

  .bar-legend li {
    display: inline-block;
  }

  .bar-legend-text {
    display: inline-block;
    padding-left: 5px;
    padding-right: 10px;
    vertical-align: middle;

}
  .bar-legend-icon {
    display: inline-block;
    top: 0;
    width: 15px;
    height: 15px;
    border-radius: 5px; 
    vertical-align: middle;
}
  .canvas {
    width: 160px!important;
    height: 160px!important;
  }

</style>
