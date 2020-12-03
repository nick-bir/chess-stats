<template>
  <svg class="inline-svg-icon">
    <use :xlink:href="svg+'#chess-figure__klt'"></use>
  </svg>
</template>

<script>
require('./ChessFigure__Sprite.svg');
export default {
  data(){
    return {
      svg: require('./ChessFigure__Sprite.svg')
    }
  }
  
}
</script>