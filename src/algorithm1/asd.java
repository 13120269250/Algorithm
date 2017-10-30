package algorithm1;

public class asd {

	public static void main(String[] args) {
		System.out.println(getPalindromeLength("asddfggfddhthgggkh"));
	}
	
	public static String getPalindromeLength(String str) {
	        // 1.构造新的字符串
	        // 为了避免奇数回文和偶数回文的不同处理问题，在原字符串中插入'#'，将所有回文变成奇数回文
	        StringBuilder newStr = new StringBuilder();
	        newStr.append('#');
	        for (int i = 0; i < str.length(); i ++) {
	            newStr.append(str.charAt(i));
	            newStr.append('#');
	        }

	        // rad[i]表示以i为中心的回文的最大半径，i至少为1，即该字符本身
	        int [] rad = new int[newStr.length()];
	        // right表示已知的回文中，最右的边界的坐标
	        int right = 0;
	        // id表示已知的回文中，拥有最右边界的回文的中点坐标
	        int id = 0;
	        // 2.计算所有的rad
	        // 这个算法是O(n)的，因为right只会随着里层while的迭代而增长，不会减少。
	        for (int i = 0; i < newStr.length(); i ++) {
	            // 2.1.确定一个最小的半径 本身
	            int r = 1;
	            
	            if (i <= right) { //这个if判断仅仅为了优化执行速度
	                r = Math.min(rad[id] - i + id, rad[2 * id - i]);
	            }
	            // 2.2.尝试更大的半径  前半段限制左右范围不能超限  围绕中心点的左右两边是否相等  如果相等就可以尝试半径扩张
	            while (i - r >= 0 && i + r < newStr.length() && newStr.charAt(i - r) == newStr.charAt(i + r)) { 
	                r++;
	            }
	            
	            // 2.3.更新边界和回文中心坐标
	            if (i + r - 1> right) {
	                right = i + r - 1;
	                id = i;
	            }
	            
	            rad[i] = r; //当前坐标下的最长半径
	        }

	        // 3.扫描一遍rad数组，找出最大的半径
	        int maxLength = 0;
	        int center = 0;
	        for (int i = 0; i < rad.length; i ++) {
	            if (rad[i] > maxLength) {
	                maxLength = rad[i] - 1;
	                center = i;
	            }
	        }
	        String substring = newStr.substring(center - maxLength, center + maxLength);
	        String replace = substring.replace("#", "");
	        return replace;
	}
}
