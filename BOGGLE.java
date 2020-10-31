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
