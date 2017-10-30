package algorithm1;

public class test {
		public static void main(String[] args) {
			int[] nums1 = {1,2};
			int[] nums2 = {3,4};
			System.out.println(sortedArrays(nums1,nums2));
		}
		
	    public static double sortedArrays(int[] nums1, int[] nums2) {
	        int length1 = nums1.length;
	        int length2 = nums2.length;
	        
	        //除法下取整
	        double med1 = (nums1[(length1 - 1) / 2] + nums1[length1 / 2]) / 2.0;
	        double med2 = (nums2[(length2 - 1) / 2] + nums2[length2 / 2]) / 2.0;
	        
	        if(length2 == 0) 
	        	return med1;
	        if(med1 > med2){
	        	
	        	
	        	
	        } 
	        
	        
			return 0;
	    }
	    
}
