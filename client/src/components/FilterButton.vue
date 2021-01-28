<template lang="pug">
a.FilterButton(@click='$emit("click")', :class='classes')
    slot
</template>

<script>
export default {
    name: 'FilterButton',
    props: ['active', 'size'],
    computed: {
        classes() {
            return {
                FilterButton_active: this.active,
                ['FilterButton_size_' + this.size]: this.size,
            };
        },
    },
};
</script>

<style lang="stylus">
.FilterButton
    display: inline-block
    padding: 5px 10px
    margin: 5px
    border: 1px solid silver
    border-radius: 5px
    box-shadow: 0 0 3px rgba(107, 106, 106, .49)
    cursor: pointer

    &:hover
        background-color: #ededed

    &_active
        background-color: #5e6d8d
        border-color: #7e7f8c
        box-shadow: none
        color: white

    &_size
        &_square-m
            width: 2em
            height: 2em
            margin: 3px
            padding: 3px
</style>