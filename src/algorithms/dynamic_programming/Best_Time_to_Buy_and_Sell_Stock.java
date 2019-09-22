package algorithms.dynamic_programming; /**
 * @author lytbf on 10/24/2018.
 */

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * <p>
 * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share
 * of the stock),
 * design an algorithm to find the maximum profit.
 * <p>
 * Note that you cannot sell a stock before you buy one
 */
class Best_Time_to_Buy_and_Sell_Stock {
	public static void main(String[] args) {
		Best_Time_to_Buy_and_Sell_Stock mt = new Best_Time_to_Buy_and_Sell_Stock();
		int[] p = {7, 1, 5, 3, 6, 4};
		System.out.println(mt.maxPro(p) + ", " + ", " + mt.maxProfit_Sol(p));
	}
	
	
	public int maxPro(int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		int maxPro = 0;
		int n = prices.length;
		int low = prices[0];
		for (int i = 1; i < n; i++) {
			if (prices[i] - low > maxPro) {
				maxPro = prices[i] - low;
			}
			if (low > prices[i]) {
				low = prices[i];
			}
		}
		return maxPro;
	}
	
	public int maxProfit(int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		int profit = 0, maxpro = 0;
		int minP = Integer.MAX_VALUE;
		for (int i = 0; i < prices.length; i++) {
			profit = Math.max(0, profit += (prices[i] - prices[i - 1]));
			maxpro = Math.max(profit, maxpro);
		}
		
		return profit;
	}
	
	public int maxProfit_Sol(int[] prices) {
		int minPrice = Integer.MAX_VALUE;
		int maxProfit = 0;
		for (int i = 0; i < prices.length; i++) {
			if (prices[i] < minPrice) {
				minPrice = prices[i];
			} else if (prices[i] - minPrice > maxProfit) {
				maxProfit = prices[i] - minPrice;
			}
		}
		return maxProfit;
	}
}
