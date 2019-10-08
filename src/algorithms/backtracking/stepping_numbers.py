from typing import List


class Solution:

    def countSteppingNumbers(self, low: int, high: int) -> List[int]:
        lis = []

        def dfs(lo: int, hi: int, step_num: int):
            if hi >= step_num >= lo:
                lis.append(step_num)
            if step_num == 0 or step_num > hi:
                return
            last_d = step_num % 10
            step_num_a = step_num * 10 + (last_d - 1)
            step_num_b = step_num * 10 + (last_d + 1)
            if last_d == 0:
                dfs(lo, hi, step_num_b)
            elif last_d == 9:
                dfs(lo, hi, step_num_a)
            else:
                dfs(lo, hi, step_num_a)
                dfs(lo, hi, step_num_b)

        for i in range(0, 10, 1):
            dfs(low, high, i)
        lis.sort()
        return lis
