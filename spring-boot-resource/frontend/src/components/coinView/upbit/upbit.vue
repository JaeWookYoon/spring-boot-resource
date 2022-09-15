<template>
    <div >업비트에</div>
    <ul class="upbit" v-if="coinList">
        <li v-for="(item,i) in coinList" ><b v-bind:class="{up:item.updn!='FALL',down:item.updn=='FALL',even:item.updn=='EVEN'}">{{i}} : {{item.contPrice}}</b></li>
    </ul>
</template>

<script src="./_upbit.js" lang="js"></script>
<style src="./_upbit.less" lang="less" scoped></style>
