<template>
  <!-- <svg class="chess-figure" viewbox="0 0 45 45">
    <use :xlink:href="svg+'#chess-figure__icon-klt'"></use>
  </svg> -->
  <div class="chess-figure" :class="`chess-figure_figure_${figure}`"></div>
</template>

<script>

export default {
  props: {
    figure: {
      default: 'ql'
    }
  }
  
}
</script>

<style lang="stylus">
  .chess-figure
    width: 100%
    height: 100%
    background-image: url('ChessFigure__Sprite.svg')
    background-size: 600% 200%

    &_figure_kl
      background-position: 0 0
    &_figure_ql
      background-position: 20% 0
    &_figure_rl
      background-position: 40% 0
    &_figure_bl
      background-position: 60% 0
    &_figure_nl
      background-position: 80% 0
    &_figure_pl
      background-position: 100% 0

    &_figure_kd
      background-position: 0 100%
    &_figure_qd
      background-position: 20% 100%
    &_figure_rd
      background-position: 40% 100%
    &_figure_bd
      background-position: 60% 100%
    &_figure_nd
      background-position: 80% 100%
    &_figure_pd
      background-position: 100% 100%
</style>
