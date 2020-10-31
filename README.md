# BOGGLE

https://algospot.com/judge/problem/read/BOGGLE

# 구현 방법

모든 경우를 찾는 방법으로는 시간초과에 걸린다. 모든 경우를 찾으면서 중복체크되는 경우를 제거해주어야 한다.
```
 ex) U R L P Y
     X P R E T
     G I A T T
     X T N Z M
     X O Q R S 와 같은 게임판이 있고
 
     PRETTY 와 같은 문자열을 만들 수 있는지 체크할때

 i)   중복을 체크하는 배열로 cache[글자의 길이][5][5] 배열을 이용한다.
 
 ii)  (1,1)에 P를 발견하여 탐색을 진행한다. 자기 주변 8칸에 있는 문자 중에 다음 문자에 해당하는 R 이 있는지 찾아본다.
      (1,2)의 R을 발견하고 다시 탐색을 진행한다.
 
 iii) (1,3)의 E를 발견하고 다시 탐색을 진행한다.
 
 iv)  (1,4)의 T를 발견하고 다시 탐색을 진행한다.
 
 v)   (2,4)의 T를 발견하고 탐색을 진행한다. 그러나 이 T 주변에 Y는 존재하지 않는다. 그렇다면 cache[4][2][4]의 값을 1로 바꾼다.
      이렇게 되면 다음번에 PRETTY의 다섯번째 문자 T가 있는지 체크할 때 (2,4)의 칸은 다시 탐색하지 않고 무시할 수 있게 된다.
      
 vi)  위의 과정을 반복하며 게임판 안에서 원하는 문자열을 만들어 낼 수 있는지 체크한다.
```

# 구현 코드
```java
import java.util.Scanner;

public class BOGGLE {

	static boolean[] result = new boolean[10];
	static int[][] dxy = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
	static char[][] board = new char[5][5];
	
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		
		int c;
		String s;
		
		c = sc.nextInt();
		
		for(int i = 0; i < c; i++)
		{
			int n;
			
			for(int k = 0; k < 5; k++)
			{
				s = sc.next();
				
				for(int j = 0; j < 5; j++)
				{
					board[k][j] = s.charAt(j);
				}
			}
			
			n = sc.nextInt();
			
			String[] in_s = new String[n];
			
			for(int j = 0; j < n; j++)
			{
				int k,k1,k2;
				
				in_s[j] = sc.next();
				
				int[][][] cache = new int[in_s[j].length()][5][5];
				
				for(k = j - 1; k >= 0; k--)
				{
					if(in_s[j].equals( in_s[k] ) == true && result[k] == true)
					{
						System.out.println(in_s[j] + " YES");
						result[j] = true;
						break;
					}
				}
				
				if(k >= 0) continue;
				
				for(k1 = 0; k1 < 5; k1++)
				{
					for(k2 = 0; k2 < 5; k2++)
					{
						if(board[k1][k2] == in_s[j].charAt(0)) 
						{
							Check(in_s[j],cache,k1,k2,0,j);
							if(result[j] == true) break;
						}
					}
					if(k2 != 5) break;
				}
				if(k1 == 5) result[j] = false;
				
				if(result[j]) System.out.println(in_s[j] + " YES");
				else System.out.println(in_s[j] + " NO");
				
			}
			
			for(int k = 0; k < 10; k++)
			{
				result[k] = false;
			}
		}
	}
	
	public static void Check (String s, int[][][] cache, int x_pos, int y_pos, int k, int j)
	{
		if(k == s.length() - 1) 
		{
			result[j] = true;
			return;
		}
		for(int i = 0; i < 8; i++)
		{
			if(x_pos + dxy[i][0] >= 5 || x_pos + dxy[i][0] < 0 || y_pos + dxy[i][1] >= 5 || y_pos + dxy[i][1] < 0) continue;
			else
			{
				if(cache[k+1][x_pos + dxy[i][0]][y_pos + dxy[i][1]] == 0 && board[x_pos + dxy[i][0]][y_pos + dxy[i][1]] == s.charAt(k+1)) 
					Check(s,cache,x_pos+dxy[i][0],y_pos+dxy[i][1],k+1,j);
				
				if(result[j] == true) return;
			}
		}
		
		cache[k][x_pos][y_pos] = 1;
		
		return;
	}

}
```
