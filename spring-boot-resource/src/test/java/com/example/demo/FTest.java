package com.example.demo;

public class FTest {

	/*
	 * @Test public void 테스트임() { List<String>s1 = null; Set<String>se1 = null;
	 * //Assert.assertThat(null, null, CoreMatchers.allOf); char[]c1 =null; String
	 * input = "aaa"; char[]ccc = input.toCharArray(); List<Character> cList = new
	 * ArrayList<>(); StringBuilder ss = new StringBuilder(); Set<String>azz = new
	 * LinkedHashSet(Arrays.asList(input.split(""))); String.join("", azz); }
	 */
	public static void main(String[]args) {
		System.out.println(simple("ghhrrkd"));
	}
	public static String simple(String input) {
		String[]s1 = input.split("");
		StringBuilder str = new StringBuilder();
		for(int i=1;i<s1.length;i+=2) {
			if(s1[i].equals(s1[i-1])) {
				str.append(s1[i-1]);
				str.append(s1[i]);
			}else {
				str.append(s1[i]);
			}
		}
		return str.toString();
	}
}
