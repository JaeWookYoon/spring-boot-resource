// @vue/component
import axios from 'axios'; 
export default {
    name: 'Upbit',

    components: {},

    mixins: [],

    props: {},

    data () {
        coinList:{}
        return {
            coinList:{},
            coinSearch:[],
            compareList:[],
            message:{},
            connection:null
        }
    },

    computed: {},

    watch: {
        compareList:function(){            
            if(this.coinList[this.compareList.code] != this.compareList.trade_price){
                    this.coinList[this.compareList.code] = {"contPrice":this.compareList.trade_price,"updn":this.compareList.change};
            }
            /* this.compareList.forEach((cur,index,item)=>{
                //console.log(cur.symbol.split("_")[0]);
                if(this.coinList[cur.code] != cur.trade_price){
                    this.coinList[cur.code] = {"contPrice":cur.trade_price,"updn":cur.change};
                }
            }); */
            console.log("upbit change");
        }
    },

    created () {        
        if(this.connection == undefined)
            this.connection = new WebSocket("wss://api.upbit.com/websocket/v1")

            this.connection.onmessage = event => {
                if (event.data instanceof Blob) {
                    var  reader = new FileReader();

                    reader.onload = () => {
                        this.compareList = JSON.parse(reader.result);
                    };

                    reader.readAsText(event.data);
                } else {
                    this.compareList = JSON.parse(event.data);
                }                                                                                                           
            }
            this.connection.onopen = event => {                
                console.log("Upbit Successfully connected to the echo websocket server...");           
                this.sendMessage();     
            }
            this.connection.onclose = event =>{
                console.log("Close Upbit Retry");
                /* while(this.connection == undefined){
                    this.connection = new WebSocket("wss://api.upbit.com/websocket/v1");
                } */
            }
        this.fetchBaseInfo();
    },

    methods: {
        sendMessage:function(){   
            var uid = this.getRequestVal();            
            this.message = [{"ticket":uid},{
                "type" : "trade", 
                "codes" : this.coinSearch
            }];                 
            //console.log(this.coinSearch);   
            //console.log(this.message);                 
            this.connection.send(JSON.stringify(this.message));            
        },
        fetchBaseInfo:function(){
            axios.get(`https://api.upbit.com/v1/market/all`) // 해당 url에서 제공하는 데이터를 가져옴.
                .then(response => {                  
                  //console.log(response.data);
                  var tempString = "";
                  response.data.forEach((cur,index,item) =>{
                    if(cur.market.includes("KRW")){
                        this.coinSearch.push(cur.market);
                        tempString += ","+cur.market;
                    }
                  });      
                  this.fetchInfo(tempString.substring(1,tempString.length));
                });             
        },
        fetchInfo:function(market){
            axios.get('https://api.upbit.com/v1/ticker?markets='+market)
            .then(response=>{
                response.data.forEach((cur,index,item)=>{
                    this.coinList[cur.market] = {"contPrice":cur.opening_price};
                });
            });
        },
        getRequestVal:function(){
            return ([1e7]+-1e3+-4e3+-8e3+-1e11).replace(/[018]/g, c =>
                (c ^ crypto.getRandomValues(new Uint8Array(1))[0] & 15 >> c / 4).toString(16)
            );
        }
    }
}
