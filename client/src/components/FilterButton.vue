<template lang="pug">
a.FilterButton(@click='$emit("click")', :class='$bem(mods)')
    slot
</template>

<script>
export default {
    name: 'FilterButton',
    props: ['active', 'size'],
    computed: {
        mods() {
            return {
                active: this.active,
                size: this.size,
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
        &:hover
            background-color: #7382a4

    &_size
        &_square-m
            width: 2em
            height: 2em
            margin: 3px
            padding: 3px
</style>