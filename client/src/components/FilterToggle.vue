<template lang="pug">
.FilterToggle(@click='$emit("click")', :class='$bem({ active })') 
    slot.FilterToggle__label off
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
    padding: 3px 5px
    cursor: pointer

    &__label
        float: left

    &__toggle
        float: right
        width: 2.5em
        height: 1.2em
        border: 1px solid silver
        border-radius: .75em
        background-color: silver
        transition: background-color .1s .1s

        &:before
            content: ''
            display: block
            width: 1.2em
            height: 1.2em
            box-sizing: border-box
            // border: 1px solid silver
            border-radius: 50%
            background-color: white
            box-shadow: 0 0 1px rgba(0, 0, 0, .7)
            transform: translateX(0%)
            transition: transform .2s
        
    &_active
        .FilterToggle__toggle
            background-color: #5e6d8d
            &:before
                transform: translateX(112%)

</style>