<template>
    <div>빗썸이에용</div>
    <ul class="bitthum" v-if="coinList">
        <li v-for="(item,i) in coinList" ><b v-bind:class="{up:item.updn=='up',down:item.updn=='dn'}">{{i}} : {{item.contPrice}}</b></li>
    </ul>
</template>

<script src="./_bitthum.js" lang="js"></script>
<style src="./_bitthum.less" lang="less" scoped></style>
