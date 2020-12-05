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

    &_figure_K
      background-position: 0 0
    &_figure_Q
      background-position: 20% 0
    &_figure_R
      background-position: 40% 0
    &_figure_B
      background-position: 60% 0
    &_figure_N
      background-position: 80% 0
    &_figure_P
      background-position: 100% 0

    &_figure_k
      background-position: 0 100%
    &_figure_q
      background-position: 20% 100%
    &_figure_r
      background-position: 40% 100%
    &_figure_b
      background-position: 60% 100%
    &_figure_n
      background-position: 80% 100%
    &_figure_p
      background-position: 100% 100%
</style>
