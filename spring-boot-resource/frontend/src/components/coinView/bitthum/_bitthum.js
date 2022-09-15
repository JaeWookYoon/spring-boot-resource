import axios from 'axios'; 
export default {
    name: 'Bitthum',

    components: {},

    mixins: [],

    props: {},

    data () {  
        return {
            coinList:{},
            compareList:[],
            coinSearch:[],
            connection : null,
            message:{}
        }
    },

    computed: {               
    },

    watch: {
        compareList:function(){
            this.compareList.forEach((cur,index,item)=>{
                //console.log(cur.symbol.split("_")[0]);
                if(this.coinList[cur.symbol.split("_")[0]] != cur.contPrice){
                    this.coinList[cur.symbol.split("_")[0]] = {"contPrice":cur.contPrice,"updn":cur.updn};
                }
            });
            //console.log("change");
        }        
    },

    created () {
         if(this.connection == undefined)
            this.connection = new WebSocket("wss://pubwss.bithumb.com/pub/ws")

            this.connection.onmessage = res => {                                                   
                if(JSON.parse(res.data).content){
                    this.compareList = JSON.parse(res.data).content.list;                    
                }                                                                                            
            }
            this.connection.onopen = event => {                
                console.log("Bitthumb Successfully connected to the echo websocket server...");           
                this.sendMessage();     
            }     
            this.fetchInfo();          
    },

    methods: {
        sendMessage:function(){   
            this.message = {
                "type" : "transaction", 
                "symbols" : this.coinSearch,
                "tickTypes": ["30M"]
            };                 
            this.connection.send(JSON.stringify(this.message));            
        },
        
        fetchInfo:function(){
           axios.get(`https://api.bithumb.com/public/ticker/ALL_KRW`) // 해당 url에서 제공하는 데이터를 가져옴.
                .then(response => {                  
                  console.log(response.data.data);
                  for(var key in response.data.data){
                      this.coinSearch.push(key+"_KRW");
                      this.coinList[key]={"contPrice":response.data.data[key].opening_price};
                  }          
                }); 
        }
    }
}
