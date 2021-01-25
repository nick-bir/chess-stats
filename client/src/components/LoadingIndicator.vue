<template lang="pug">
    .LoadingIndicator 
        .LoadingIndicator__paranja 
        .LoadingIndicator__text loading
</template>

<script>
export default {
    name: 'LoadingIndicator',
};
</script>

<style lang="stylus">
    .LoadingIndicator 
        position: absolute
        top: 0
        left: 0
        right: 0
        bottom: 0
        display: flex
        align-items: center
        justify-content: center
        &__paranja
            position: absolute
            top: 0
            left: 0
            right: 0
            bottom: 0
            background-color: rgba(0, 0, 0, .3)
            z-index: 0
        &__text
            position: relative
            padding: 5px 15px
            color: white
            background-color: rgb(128, 128, 128)
            // filter: drop-shadow(0 0 5px rgba(0,0,0,.6))
            filter: drop-shadow(0 0 3px rgba(0, 0, 0, 0.65))
            border-radius: 15px
            &:after
                content: ''
                position: absolute
                left: 100%
                top: 50%
                transform: translate(-50%, -50%)
                width: 22px
                height: 22px
                border-radius: 50%
                background-color: rgba(66, 66, 66, 0.67)
                animation: left-right 1s infinite alternate-reverse linear
                z-index: 1


    @keyframes left-right {
        from {
            left: 80%
        }
        to {

            left: 20%
        }
    }
        


</style>