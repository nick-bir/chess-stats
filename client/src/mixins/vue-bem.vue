<script>
export default {
    methods: {
        $bem(bemOptions) {
            let bemClasses = {};
            const elem = bemOptions.elem;
            const block = bemOptions.block || this.$options.name;
            const mods = Object.entries(bemOptions.mods || bemOptions);
            const suffix = block + (elem ? '__' + elem : '');

            if (suffix) {
                bemClasses[suffix] = true;
            }

            for (let [mod, val] of mods) {
                const skipBlockAndElem = bemOptions.mods
                    ? false
                    : mod === 'block' || mod === 'elem';
                if (skipBlockAndElem) {
                    continue;
                }
                let modkey = suffix + '_' + mod;
                if (typeof val !== 'boolean') {
                    modkey += '_' + val;
                }
                bemClasses[modkey] = Boolean(val);
            }

            return bemClasses;
        },
        $bemElem(elemName, bemOptions) {
            return this.$bem({ ...bemOptions, elem: elemName });
        },
    },
};
</script>