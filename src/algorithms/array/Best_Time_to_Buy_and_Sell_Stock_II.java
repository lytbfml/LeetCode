package algorithms.array; /**
 * @author lytbf on 10/24/2018.
 */

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * design an algorithm to find the maximum profit. You may complete as many transactions as you like
 * (i.e., buy one and sell one share of the stock multiple times).
 * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
 */
class Best_Time_to_Buy_and_Sell_Stock_II {
	
	public int maxProfit(int[] prices) {
		if (prices.length == 0) {
			return 0;
		}
		
		int profit = 0;
		int temp1 = prices[0];
		for (int i = 1; i < prices.length; i++) {
			if (prices[i] <= temp1) {
				temp1 = prices[i];
			} else {
				profit += (prices[i] - temp1);
				temp1 = prices[i];
			}
		}
		
		return profit;
	}
	
	public int maxProfit_Sol(int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		
		int result = 0;
		
		for (int i = 1; i < prices.length; i++) {
			if (prices[i] > prices[i - 1])
				result += prices[i] - prices[i - 1];
		}
		
		return result;
	}
}
