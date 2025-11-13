
public class BinarySearch {
	
    static void insertionSort(int arr[]) {
        for (int i=1; i<arr.length; i++) {
            int key=arr[i];
            int j=i-1;
            while (j>=0 && arr[j]>key) {
                arr[j+1]=arr[j];
                j--;
            }
            arr[j+1]=key;
        }
    }

	public static int binarySearch(int arr[], int l, int h, int key) {
		if(l>h) return -1;
		int mid=(l+h)/2;
		if(arr[mid]==key) {
			return mid;
		}
		else if(key>arr[mid]) {
			return binarySearch(arr,mid+1,h,key);
		}
		else {
			return binarySearch(arr,l,mid-1,key);
		}
		
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[]= {1002,110,23445,6789,9807,6455,75432};
		int key=110;
		insertionSort(arr);
		int ans=binarySearch(arr,0,arr.length-1,key);
		if(ans!=-1) {
			System.out.println("Element found at index: "+ans);
		}
		else {
			System.out.println("Element not found in the array.");
		}
//		boolean flag=false;
//		int ans=0;
//		int l=0;
//		int h=arr.length-1;
//		while(l<=h){
//			int mid=(l+h)/2;
//			if(arr[mid]==key) {
//				flag=true;
//				ans=mid;
//				System.out.println("Key Found!");
//				break;
//			}
//			else if(key>arr[mid]) {
//				l=mid+1;
//			}
//			else {
//				h=mid-1;
//			}
//		}
//		if(flag==true) {
//			System.out.println("Element Found at index "+ans);
//		}
//		else {
//			System.out.println("Element Not Found");
//		}
//		
//
	}

}
