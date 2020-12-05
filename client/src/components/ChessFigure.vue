<template>
  <svg class="ChessFigure">
    <use :xlink:href="svg+'#chess-figure__icon-klt'"></use>
  </svg>
</template>

<script>

export default {
  data(){
    return {
      svg: require('./ChessFigure__Sprite.svg')
    }
  }
  
}
</script>

<style lang="stylus">
  .ChessFigure
    width: 100%
    height: 100%

</style>
