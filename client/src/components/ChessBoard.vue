<template lang="pug">
    div.chess-board
        div.chess-board__content
            .chess-board__row(v-for="r in 8")
                .chess-board__tile(v-for="c in 8")
                    .chess-board__tile-content
                        |{{r}}:{{c}}
                        ChessFigure
                

    </div>
</template>

<script>
import ChessFigure from './ChessFigure'
export default {
    name: 'ChessBoard',
    components: {
        ChessFigure
    },
    created() {
        console.log('----chessBoard created');
    }
}
</script>

<style lang="stylus">
    dev-border()
        border: 1px solid silver;

    responsive-square() 
        dev-border()
        position: relative
        &:after
            content: ''
            display: block
            padding-bottom: 100%
        &__content, &-content
            position: absolute;
            width: 100%
            height: 100%
    
    .chess-board
        responsive-square()
        width: 50%
        margin: auto
        border: 1px solid silver

        &__row
            dev-border()
            box-sizing: border-box;
            width: 100%
            height: 12.5%
        
        &__tile
            responsive-square()
            display: inline-block
            box-sizing: border-box
            width: 12.5%
            &:hover
                border-color: red
        &__tile-content
            display: flex
            justify-content: center
            align-items: center



</style>