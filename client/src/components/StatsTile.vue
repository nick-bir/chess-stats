<template>
    <div class="StatsTile">{{ roundPerc }}</div>
</template>

<script>
export default {
    props: ['percentage'],

    computed: {
        roundPerc() { 
            return Math.round(this.percentage * 100) / 100; 
        }
    }
}
</script>