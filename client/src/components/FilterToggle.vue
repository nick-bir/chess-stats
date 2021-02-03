<template lang="pug">
.FilterToggle(@click='$emit("click")', :class='$bem({ active })') 
    .FilterToggle__label
        slot off
    .FilterToggle__toggle
</template>

<script>
export default {
    name: 'FilterToggle',
    props: ['active']
}
</script>

<style lang="stylus">
.FilterToggle
    display: flex
    align-items: center
    justify-content: space-between
    padding: 3px 5px
    cursor: pointer

    &__label
        float: left

    &__toggle
        position: relative
        float: right
        width: 2.5em
        height: 1.2em
        border: 1px solid silver
        border-radius: .75em
        // background-color: silver
        background-color: #f0eeee
        transition: background-color .1s .1s

        &:after
            content: ''
            position: absolute
            left: 0
            width: 1.2em
            height: 1.2em
            box-sizing: border-box
            border-radius: 50%
            background-color: #a4a2a2
            border: 5px solid white
            box-shadow: 0 0 1px rgba(0, 0, 0, .7)
            transform: translateX(0%)
            transition: all .07s ease-in


    &:hover
        .FilterToggle__label
            color: #3c3a3a
        
    &_active
        .FilterToggle__toggle
            background-color: #5e6d8d
            &:after
                left: 100%
                transform: translateX(-100%)
                background-color: #5e6d8d


</style>