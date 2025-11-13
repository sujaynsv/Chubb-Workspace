
public class LinearSearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int key=17;
		int arr[]= {2,6,7,17,29,31,67,89,101};
		int ans=0;
		boolean flag=false;
		for(int i=0;i<arr.length;i++) {
			if(arr[i]==key) {
				flag=true;
				ans=i;
				break;
			}
		}
		if(flag) {
			System.out.println("Element found at index "+ans);
		}
		else {
			System.out.println("Element not found in the array.");
		}

	}

}
