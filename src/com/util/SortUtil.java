package com.util;

public class SortUtil {

	public static void main(String[] args) {

			//bulleSort();
			selectionSort();
	}

	/**
	 * 冒泡排序
	 * 算法思想：遍历待排序的数组，每次遍历比较相邻的两个元素，如果他们的排列顺序错误就交换他们的位置，经过一趟排序后，最大的元素会浮置数组的末端。重复操作，直到排序完成。
	 */
	
	public static  void   bulleSort(){
		//从大向小排序
		int score[] = { 67, 69, 75, 87, 89, 90, 68, 100 };
		for (int i = 0; i < score.length-1; i++) {  //最多做n-1趟排序
			
			for (int j = 0; j < score.length-i-1; j++) { //对当前无序区间score[0......length-i-1]进行排序(j的范围很关键，这个范围是在逐步缩小的)
				
				if (score[j]<score[j + 1]) {//把小的值交换到后面
					int temp = score[j];
					score[j] = score[j + 1];
					score[j + 1] = temp;
					
				}
				
			}
			
			System.out.print("第" + (i + 1) + "次排序结果：");
			for (int a = 0; a < score.length; a++) {
				System.out.print(score[a] + "\t");
			}
			System.out.println("");
			
		}
		
		System.out.print("最终排序结果：");
		for (int a = 0; a < score.length; a++) {
			System.out.print(score[a] + "\t");
		}
		
		
		
	}

	/**
	 * 选择排序
	 * 算法思想：重待排序的数组中选择一个最小的元素，将它与数组的第一个位置的元素交换位置。然后从剩下的元素中选择一个最小的元素，将它与第二个位置的元素交换位置，
	 *         如果最小元素就是该位置的元素，就将它和自身交换位置，依次类推，直到排序完成。
	 */
	
	public static void selectionSort(){
		
		int score[] = { 67, 69, 75, 87, 89, 90, 68, 100 };

		for (int i = 0; i < score.length; i++) {
			
			int min = i;
			for (int j = i+1; j < score.length; j++) {
			     
				if (score[j]<score[min]) {
					min =j;
				}

			}
			int temp=score[min];
			score[min]=score[i];
			score[i]=temp;	
		}
		
		System.out.print("最终排序结果：");
		for (int a = 0; a < score.length; a++) {
			System.out.print(score[a] + "\t");
		}
	
	}
	
	
	
	
}
