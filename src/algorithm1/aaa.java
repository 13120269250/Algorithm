package algorithm1;

public class aaa {

	public static void main(String[] args) {  
		
	        int max = 90;   
	        try {  
	        	String string = args[0];
	            max = Integer.parseInt(string);  
	        }   
	        catch (Exception e) {  
	        }   
	        boolean[] isprime = new boolean[max + 1];  
	  
	        for (int i = 0; i <= max; i++)  
	            isprime[i] = true;  
	  
	        isprime[0] = isprime[1] = false;  
	  
	        int n = (int) Math.ceil(Math.sqrt(max));   
	  
	        for (int i = 0; i <= n; i++) {  
	            if (isprime[i])   
	                for (int j = i * i; j <= max; j = j + i)   //从i*i而不从i*2开始，是因为已经i*3，i*2早已经被2，3筛过了。

	                    isprime[j] = false;   
	        }  
	        int largest;  
	        for (largest = max; !isprime[largest]; largest--);   
	  
	        System.out.println("The largest prime less than or equal to " + max  
	                + " is " + largest);  
	}  
	//区间筛法
}//先分别做好[2,sqrt(b))的表和[a,b)的表，然后从[2,sqrt(b))的表中筛得素数的同时，也将其倍数从[a,b)的表中划去，最后剩下的就是区间[a,b)内的素数了
/*int FindPrime ( int MaxN ) {

for( int i = 2 ; i <= MaxN ; ++i ){ 不用返回值的情况下  ++i比i++效率高

    if( IsPrime[ i ] )

        Pri[ PriN++ ]=i; //第一个质数开始装

    for(int j=0;j<PriN;++j){

        if( i*Pri[ j ] > MaxN )

            break; //当过大了就跳出

        IsPrime[ i * Pri[ j ] ] = 0;

        //筛去素数

        if( i % Pri[ j ] == 0 ) break;

        //这里是关键,如果i是一个合数(这当然是允许的)而且i mod prime[j] = 0

       

    }

}*/