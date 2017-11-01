package algorithm1;

public class LeetCode2 {
	
	public static void main(String[] args) {
		
		ListNode l11 = new ListNode(2);
		ListNode l12 = new ListNode(4);
		ListNode l13 = new ListNode(3);
		
		ListNode l21 = new ListNode(5);
		ListNode l22 = new ListNode(6);
		ListNode l23 = new ListNode(4);
		
		l11.next = l12;
		l12.next = l13;
		
		l21.next = l22;
		l22.next = l23;
		
		addTwoNumbers(l11, l21);
		
	} 
		
	/**
	 * LeetCode第二题 Add Two Numbers
	 * Input: (2 -> 4 -> 3) +
	 * 		  (5 -> 6 -> 4)
	 * Output: 7 -> 0 -> 8
	 */
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
	       int carry =0;
	 
	        ListNode newHead = new ListNode(0);
	        ListNode p1 = l1, p2 = l2, p3=newHead;
	 
	        while(p1 != null || p2 != null){
	            if(p1 != null){
	                carry += p1.val;
	                p1 = p1.next;
	            }
	 
	            if(p2 != null){
	                carry += p2.val;
	                p2 = p2.next;
	            }
	 
	            p3.next = new ListNode(carry%10);
	            p3 = p3.next;
	            carry /= 10;
	        }
	 
	        if(carry==1) 
	            p3.next=new ListNode(1);
	 
	        return newHead.next;
	    }
	
}

class ListNode {
	int val;
	ListNode next;
	ListNode(int x) { val = x; }
}
