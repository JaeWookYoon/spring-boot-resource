package com.jwyoon.www.elastic;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;

@SuppressWarnings("resource")
public class ElasticsearchConnection {
	
	private static final String hostname = "localhost";
	private static final int port = 9200;
	private static final String scheme = "http";
	
	private static RestClientBuilder clientBuilder;
	
	static {
		HttpHost host = new HttpHost(hostname,port,scheme);
		clientBuilder = RestClient.builder(host);
	}
//	private Logger logger = LoggerFactory.getLogger(getClass());	
//	private Settings setting = Settings.builder().put("cluster.name", "sks").put("node.name", "sks-master") // elastic search �젙蹂� Setting
//			.put("node.name", "sks-data1").put("node.name", "sks-data2")//cluster�젙蹂� 諛� 留덉뒪�꽣,�뜲�씠�꽣 �끂�뱶 �벑濡�
//			.build();
//	private TransportClient client;
//	{//釉붾윮 珥덇린�솕 �떎�뻾
//		try {
//			client = new PreBuiltTransportClient(setting) // setting媛믪쑝濡� 遺��꽣 client 媛앹껜 �깮�꽦
//					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("172.16.100.251"), 9300)); // elastic master node �젙蹂�
//					// 9200 - elastic data �솗�씤 port , 9300 transport client �궗�슜 port
//		} catch (UnknownHostException e) {
//			logger.error(e.getMessage());	
//			logger.info("紐⑤뱢?");
//		}
//	}
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	public boolean indexExist(String year) {// index媛� 議댁옱�븷 寃쎌슦, �뿬遺� �솗�씤			
//		ImmutableOpenMap map =client.admin().cluster().state(new ClusterStateRequest()).actionGet().getState().getMetaData().getIndices();
//		boolean ex = false;
//		for(int i=0;i<map.size();i++) {// map�삎�깭濡� index name list 異쒕젰
//			ex = map.containsKey("cve-"+year);						
//		}
//		logger.info(String.valueOf(ex));
//		return ex;
//	}
//	@SuppressWarnings("unused")
//	public void deleteIndex(String year) {// �빐�떦�븯�뒗 year�쓽 index�궘�젣		
//		try {			
//			DeleteIndexResponse response = client.admin().indices().delete(new DeleteIndexRequest("cve-"+year)).actionGet();// �궘�젣 �떎�뻾
//		} catch (Exception e) {
//			e.printStackTrace();			
//		}
//	}
//	@Deprecated
//	public void insertDatas(String str,String year) throws org.json.simple.parser.ParseException, IOException {
//		JSONObject obj = new JSONObject();
//		JSONParser parser = new JSONParser();
//		IndexRequest response;
//						
//		BulkRequestBuilder bulkRequest = client.prepareBulk();//client�쓽 bulk index 以�鍮�		
//		BulkResponse bulkResponse = null;
//		StringBuffer builder = new StringBuffer();
//		
//		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(str))));
//		try {
//			String strs = "";
//			
//			while ((strs = br.readLine()) != null) {	//�씫�뼱�삩 json �뙆�씪�쓣 stringbuffer濡� �씫�뼱�샃�땲�떎.						
//				builder.append(strs);				
//			}
//		} catch (IOException ie) {
//			logger.error(ie.getMessage());
//		}
//		
//		Object obje = parser.parse(builder.toString());//stringbuffer濡� �씫�� json�뜲�씠�꽣瑜�  �뙆�떛�빀�땲�떎.
//		obj = (JSONObject) obje;
//		JSONArray arr = (JSONArray) obj.get("CVE_Items");//媛��옣 �겙 移댄뀒怨좊━ CVE_Items瑜� 媛��졇�샃�땲�떎.
//	
//		XContentBuilder build = XContentFactory.jsonBuilder().startObject();//startObject �뒗 json�쓽 {
//		/***************************************** �슦�꽑 湲곕낯 �꽕�젙 �뜲�씠�꽣 bulk index **********************************************/
//		build.field("CVE_data_type", obj.get("CVE_data_type"));
//		build.field("CVE_data_format", obj.get("CVE_data_format"));
//		build.field("CVE_data_version", obj.get("CVE_data_version"));
//		build.field("CVE_data_numberOfCVEs", obj.get("CVE_data_numberOfCVEs"));
//		build.field("CVE_data_timestamp", obj.get("CVE_data_timestamp"));
//		build.field("collect_date", new Date(System.currentTimeMillis()));
//		build.endObject();//endObject �뒗 
//		response = new IndexRequest("cve-" + year, "nvdcve").source(build);
//		
//		bulkRequest.add(response);
//		/***************************************** �슦�꽑 湲곕낯 �꽕�젙 �뜲�씠�꽣 bulk index **********************************************/
//		
//		XContentBuilder build1 = null;		
//		for (int i = 0; i < arr.size(); i++) {		//json�뙆�씪 	
//			build1 = XContentFactory.jsonBuilder().startObject();//Start XBuilder
//
//			JSONObject a = (JSONObject) arr.get(i);
//			
//			build1.startObject("CVE_ITEMS");//Start CVE-Items
//			/********************************* 5媛�吏� �겙 Category濡� field �궫�엯 *******************************************/
//			//媛곴컖�쓽 Category�뱾�쓽 �븯�쐞 移댄뀒怨좊━�뱾�� �옄�룞 json�뙆�떛�릺�뼱 �듃由ш뎄議곌� �삎�꽦�맗�땲�떎.
//			build1.field("cve", a.get("cve"));
//			build1.field("configurations", a.get("configurations"));
//			build1.field("impact", a.get("impact"));
//			build1.field("publishedDate", a.get("publishedDate"));
//			build1.field("lastModifiedDate", a.get("lastModifiedDate"));
//			/********************************* 5媛�吏� �겙 Category濡� field �궫�엯 *******************************************/
//			build1.endObject();// end cve
//			build1.endObject();// end XBuilder
//					
//			response = new IndexRequest("cve-" + year, "nvdcve").source(build1);//type : nvdcve , index : cve- �빐�떦 �뀈�룄 
//									
//			bulkResponse = bulkRequest.add(response).get();//bulk index �떎�뻾						
//		}				
//		br.close();		
//	}	
//	public void insertData(String fileAddress, String year) throws UnknownHostException, ParseException, IOException,
//			java.text.ParseException, org.json.simple.parser.ParseException {		
//		JSONObject obj = new JSONObject();
//		JSONParser parser = new JSONParser();
//		IndexRequest response;		
//		BulkProcessor bulk = getBulk(client, 10000);		
//		StringBuffer builder = new StringBuffer();		
//		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileAddress))));//json �뙆�씪 read
//		try {
//			String strs = "";
//			
//			while ((strs = br.readLine()) != null) {							
//				builder.append(strs);	//json �뙆�씪 �뜲�씠�꽣瑜� stringbuffer�뿉 �떞�쓬			
//			}
//		} catch (IOException ie) {
//			logger.error(ie.getMessage());
//		}		
//		Object obje = parser.parse(builder.toString());
//		obj = (JSONObject) obje;
//		JSONArray arr = (JSONArray) obj.get("CVE_Items");
//	
//		XContentBuilder build = XContentFactory.jsonBuilder().startObject();
//		/***************************************** �슦�꽑 湲곕낯 �꽕�젙 �뜲�씠�꽣 bulk index **********************************************/
//		build.field("CVE_data_type", obj.get("CVE_data_type"));
//		build.field("CVE_data_format", obj.get("CVE_data_format"));
//		build.field("CVE_data_version", obj.get("CVE_data_version"));
//		build.field("CVE_data_numberOfCVEs", obj.get("CVE_data_numberOfCVEs"));
//		build.field("CVE_data_timestamp", obj.get("CVE_data_timestamp"));
//		build.field("collect_date", new Date(System.currentTimeMillis()));
//		build.endObject();
//		response = new IndexRequest("cve-" + year, "nvdcve").source(build);
//
//		bulk.add(response);
//		/***************************************** �슦�꽑 湲곕낯 �꽕�젙 �뜲�씠�꽣 bulk index **********************************************/
//		
//		XContentBuilder build1 = null;		
//		for (int i = 0; i < arr.size(); i++) {			
//			build1 = XContentFactory.jsonBuilder().startObject();//Start XBuilder
//			JSONObject a = (JSONObject) arr.get(i);//Start cve
//			
//			build1.startObject("CVE_ITEMS");
//			/********************************* 5媛�吏� �겙 Category濡� field �궫�엯 *******************************************/
//			//媛곴컖�쓽 Category�뱾�쓽 �븯�쐞 移댄뀒怨좊━�뱾�� �옄�룞 json�뙆�떛�릺�뼱 �듃由ш뎄議곌� �삎�꽦�맗�땲�떎.
//			build1.field("cve", a.get("cve"));
//			build1.field("configurations", a.get("configurations"));
//			build1.field("impact", a.get("impact"));
//			build1.field("publishedDate", a.get("publishedDate"));
//			build1.field("lastModifiedDate", a.get("lastModifiedDate"));
//			/********************************* 5媛�吏� �겙 Category濡� field �궫�엯 *******************************************/
//			build1.endObject();// end cve
//			build1.endObject();// end XBuilder
//					
//			response = new IndexRequest("cve-" + year, "nvdcve").source(build1); // type : nvdcve , index : cve-�빐�떦�뀈�룄
//									
//			bulk.add(response);			
//		}		
//		bulk.close();		
//		br.close();		
//	}
//
//	@SuppressWarnings("unused")
//	public void createIndex(String year) throws IOException {
//
//		XContentBuilder build = XContentFactory.jsonBuilder().startObject().startObject("nvdcve")
//				.startObject("properties");
//		build.startObject("publishedDate");
//		build.field("type", "date");
//		build.endObject();// end publish
//		build.startObject("lastModifiedDate");
//		build.field("type", "date");
//		build.endObject();// end lastModify
//		build.endObject();// end properties
//
//		build.endObject();// end type
//		build.endObject();// end XBuilder
//		IndexResponse response = client.prepareIndex("cve-" + year, "nvdcve").setSource(build).execute().actionGet();
//
//	}
//
//	public BulkProcessor getBulk(Client client, int bulkCount) {
//		BulkProcessor bulkProcessor = BulkProcessor.builder(client, new BulkProcessor.Listener() {
//			
//			public void beforeBulk(long extensionId, BulkRequest request) {
//				// TODO Auto-generated method stub
//				request.numberOfActions();
//				logger.info("beforeBuld");									
//			}
//			public void afterBulk(long extensionId, BulkRequest request, Throwable failure) {
//				// TODO Auto-generated method stub
//				request.numberOfActions();
//				logger.info(failure.getMessage());
//				failure.printStackTrace();
//				logger.info("afterBulk");
//			}
//			public void afterBulk(long extensionId, BulkRequest request, BulkResponse response) {
//				// TODO Auto-generated method stub
//				request.numberOfActions();
//
//				logger.info(String.valueOf(response.hasFailures()));
//				logger.info(response.buildFailureMessage());
//				logger.info("afterBulk");
//			}
//		}).setBulkActions(bulkCount).setBulkSize(new ByteSizeValue(3, ByteSizeUnit.GB))
//				.setFlushInterval(TimeValue.timeValueSeconds(5)).setConcurrentRequests(10)
//				.setBackoffPolicy(BackoffPolicy.exponentialBackoff(TimeValue.timeValueMillis(100), 3)).build();
//		return bulkProcessor;
//	}
//
//	public long searchDocument() {						
//		SearchResponse search = client.prepareSearch("cve-2002").setTypes("nvdcve")
//				.setQuery(QueryBuilders.termQuery("CVE_ITEMS.cve.CVE_data_meta.ID", "CVE-1999-0019"))
//				.setFrom(0).setSize(9000).setExplain(true)
//				.get();
//		SearchHits hit = search.getHits();
//		for(int i=0;i<hit.totalHits;i++) {
//			SearchHit hi = hit.getAt(i);
//			Map<String,Object>map = hi.getSource();
//			JSONObject obj = new JSONObject(map);
//			System.out.println(obj.toJSONString());
//		}
//		logger.info(String.valueOf(search.getHits().totalHits) + "lognow");
//		return search.getHits().totalHits;		
//	}	
}
