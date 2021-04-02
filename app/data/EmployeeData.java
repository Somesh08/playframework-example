package data;

import java.util.ArrayList;
import java.util.List;

public class EmployeeData {

	  private List<Data> empData;

	    public EmployeeData() {
	        
	        this.empData = new ArrayList<Data>();
	    }

		public List<Data> getEmpData() {
			return empData;
		}

		public void setEmpData(List<Data> empData) {
			this.empData = empData;
		}

}
