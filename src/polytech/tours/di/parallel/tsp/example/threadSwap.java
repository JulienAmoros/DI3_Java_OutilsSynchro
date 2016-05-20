package polytech.tours.di.parallel.tsp.example;

import java.util.concurrent.Callable;

import polytech.tours.di.parallel.tsp.Solution;
import polytech.tours.di.parallel.tsp.TSPCostCalculator;

public class threadSwap implements Callable<Solution> {

	private Solution solution;
	
	public threadSwap(Solution sol){
		this.solution = sol;
	}
	
	@Override
	public Solution call() throws Exception {
		
		solution = myAlgorithm(solution);
		
		return solution;
	}
	
	public Solution myAlgorithm(Solution solution){
		int i, j;
		boolean notBestSol = true;
		Solution testedSol;
		TSPCostCalculator costCalc = new TSPCostCalculator();
		solution.setOF(costCalc.calcOF(solution));

		while(notBestSol)
		{
			notBestSol = false;
			
			for(i = 0; i < solution.size(); i++)
				for(j = i + 1; j < solution.size(); j++)	//Look for best solution
				{
					testedSol = solution.clone();
					testedSol.swap(i, j);
					testedSol.setOF(costCalc.calcOF(testedSol));
					if(testedSol.getOF() < solution.getOF())
					{
						solution = testedSol;
						notBestSol = true;
					}
					
					/*if(costCalc.interestingSwap(solution, i, j))
					{
						solution.swap(i, j);
						solution.setOF(costCalc.calcOF(solution));
						notBestSol = true;
					}*/
					//TODO swap � la fin
				}
		}
		//System.out.println(solution);
		return solution;
	}

}
