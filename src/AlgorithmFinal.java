import java.util.*;
public class AlgorithmFinal{
	//this is the 2d array that will be taken from the class file to update the list in the app.
	// Assuming you create a new instance of AlgorithmFinal() x; currList = x.magic();
	//this should be done every after use of updateSched() method to keep consistency;
	public static String[][] magic = new String[28][101];
	
	/*public static void main(String[] args){
		Scanner migs = new Scanner(System.in);
		for(int i=0;i<28;i++){
			String k = migs.nextLine();
			String[] temp = k.split(" ");
			for(int ii=0; ii<temp.length;ii++){
				magic[i][ii] = temp[ii];
			}
		}
		Stack<String[]> finalValues =Sorter(Compress(magic));
		for(int i=0; i<28;i++){
			String[] temp = finalValues.pop();
			for(int ii=0;ii<temp.length;ii++){
				System.out.print(temp[ii]+" ");		
			}
			System.out.println();
		}
		String[] update = new String[4];
		String[] update2 = new String[4];
		update[3] = "0";
		update[0] = "111";
		update2[3] = "0";
		update2[0] = "112";
		Stack<String[]> finalValues2 = updateSched(magic,update);
		for(int i=0; i<28;i++){
			String[] temp = finalValues2.pop();
			for(int ii=0;ii<temp.length;ii++){
				System.out.print(temp[ii]+" ");		
			}
			System.out.println();
		}
		Stack<String[]> finalValues3 = updateSched(magic,update2);
		for(int i=0; i<28;i++){
			String[] temp = finalValues3.pop();
			for(int ii=0;ii<temp.length;ii++){
				System.out.print(temp[ii]+" ");		
			}
			System.out.println();
		}
	}*/
	
	
	public AlgorithmFinal(){
	}
	//String[][] values contains sched
	//The String[][] is gonna be provided by jaudric from the database(?)											
	//compression for the sorting algorithm
	//turns String[][] to ArrayList<String[]>
	
	public static ArrayList<String[]> Compress(String[][] values){
		ArrayList<String[]> sched = new ArrayList<String[]>();
		for(int i=0;i<28;i++){//28 time slots if interval of 30 minutes. Most likely buggy part if i counted wrong. but i doubt. this is from 7am - 9pm
			int x = values[i].length;
			String[] temp = new String[x];
			for(int ii=0;ii<x;ii++){
				temp[ii] = values[i][ii];
			}
			sched.add(temp);
		}
		return sched;
	}
	
	//Sorting
	//Uses String array with format: numberSched Member1 Member2 Member3 Member4 Member5
	//Easier for array parsing.
	//this sorts from smallest to biggest
	//automatically returns a properly sorted stack<String[]> index[0] is the time block of what sched it is.
	public static Stack<String[]> Sorter(String[][] list){
		ArrayList<String[]> sched = Compress(list);
		int limit = sched.size();
		for(int i=0;i<limit;i++){
			for(int ii=1;ii<limit;ii++){
				String[] compList = sched.get(ii);
				String[] currList = sched.get(ii-1);
				String[] compList2 = Shrink(compList);
				String[] currList2 = Shrink(currList);
				int k = compList2.length;
				int kk = currList2.length;
				//if(k>kk){
                                if(k<kk) {
					//System.out.println(k+ " "+kk);
					//System.out.println("sorting");
					sched.set(ii-1, compList);
					sched.set(ii,currList);
				}
			}
		}
		return Prioritize(sched);
	}
	//counts for the real number of elements
	public static int Counter(String[] list){
		int lim =0;
		for(int i=1;i<list.length;i++){
			if(list[i]==null){lim=i;break;}
		}
		return lim;
	}
	
	//shrinks the array to take out useless empty elements;
	public static String[] Shrink(String[] list){
		String[] newArr = new String[Counter(list)];
		for(int i=0;i<newArr.length;i++){
			//System.out.println("counting");
			newArr[i] = list[i];
		}
		return newArr;
	}

	//user ID start end nameSubject DaysOfWeek
	//events dayOfMon Mon time/block free/not
	//GroupID name start nBlockks notThere	
	public static Stack<String[]> updateSched(String[][] list, String[] update){
		int x = Integer.parseInt(update[3]);
		String[] temp = list[x];
		for(int i=0;i<temp.length;i++){
			if(temp[i] == null){
			temp[i] = update[0];
			break;
			}
		}
		magic = list;
		list[x] = temp;
		return Sorter(list);
	}
	
	//puts values from sorted list to the stack for priority.
	//stack contains biggest values to the smallest
	public static Stack<String[]> Prioritize(ArrayList<String[]> sched){
		Stack<String[]> finalValues = new Stack<String[]>();
		for(int i=(sched.size()-1);i>=0; i--){
			finalValues.push(Shrink(sched.get(i)));
		}
		return finalValues;
	}
}