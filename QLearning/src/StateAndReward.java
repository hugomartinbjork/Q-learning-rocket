public class StateAndReward {

	/* State discretization function for the angle controller */
	public static String getStateAngle(double angle, double vx, double vy) {

		/* TODO: IMPLEMENT THIS FUNCTION */

		String state = "";
		int discrete = discretize2(angle, 4, -Math.PI, Math.PI);

		if (discrete == 0) {
			state = "Southwest";
			return state;
		} else if (discrete == 1) {
			state = "Northwest";
			return state;
		} else if (discrete == 2) {
			state = "Northeast";
			return state;
		} else if (discrete == 3) {
			state = "Southeast";
			return state;
		}

		return state;
	}

	/* Reward function for the angle controller */
	public static double getRewardAngle(double angle, double vx, double vy) {

		/* TODO: IMPLEMENT THIS FUNCTION */

		double reward = 0;
		reward = (Math.PI - Math.abs(angle));

		return reward;
	}

	/* State discretization function for the full hover controller */
	public static String getStateHover(double angle, double vx , double vy) {
		/* TODO: IMPLEMENT THIS FUNCTION */

//		int states = discretize2(angle, 2, -1, 1);
		String state = "";
		String vertical = "";	
		int discrete = discretize2(angle, 4, -Math.PI, Math.PI);
		int discrete_vertical =discretize(vy, 2, 0, 0);
		
		
		if (discrete_vertical==0) {
			 vertical = "up";	
		} else {
			 vertical = "down";	
		}

		if (discrete == 0) {
			state = "Southwest" + vertical;
			return state;
		} else if (discrete == 1) {
			state = "Northwest" + vertical;
			return state;
		} else if (discrete == 2) {
			state = "Northeast" + vertical;
			return state;
		} else if (discrete == 3) {
			state = "Southeast" + vertical;
			return state;
		}
		return state;
	}

	/* Reward function for the full hover controller */
	public static double getRewardHover(double angle, double vx, double vy) {

		/* TODO: IMPLEMENT THIS FUNCTION */

		double reward = 0;
		reward = (Math.PI - Math.abs(angle)) - 2*Math.abs(vy);

		return reward;
	}

	// ///////////////////////////////////////////////////////////
	// discretize() performs a uniform discretization of the
	// value parameter.
	// It returns an integer between 0 and nrValues-1.
	// The min and max parameters are used to specify the interval
	// for the discretization.
	// If the value is lower than min, 0 is returned
	// If the value is higher than min, nrValues-1 is returned
	// otherwise a value between 1 and nrValues-2 is returned.
	//
	// Use discretize2() if you want a discretization method that does
	// not handle values lower than min and higher than max.
	// ///////////////////////////////////////////////////////////
	public static int discretize(double value, int nrValues, double min, double max) {
//		System.out.println("this is the value: "+ value + "  nrValues:  "+ nrValues);

		if (nrValues < 2) {
			return 0;
		}

		double diff = max - min;

		if (value < min) {
			return 0;
		}
		if (value > max) {
			return nrValues - 1;
		}

		double tempValue = value - min;
		double ratio = tempValue / diff;

		return (int) (ratio * (nrValues - 2)) + 1;
	}

	// ///////////////////////////////////////////////////////////
	// discretize2() performs a uniform discretization of the
	// value parameter.
	// It returns an integer between 0 and nrValues-1.
	// The min and max parameters are used to specify the interval
	// for the discretization.
	// If the value is lower than min, 0 is returned
	// If the value is higher than min, nrValues-1 is returned
	// otherwise a value between 0 and nrValues-1 is returned.
	// ///////////////////////////////////////////////////////////
	public static int discretize2(double value, int nrValues, double min, double max) {
//		System.out.println("this is the value: "+ value + "  nrValues:  "+ nrValues);
		double diff = max - min;

		if (value < min) {
			return 0;
		}
		if (value > max) {
			return nrValues - 1;
		}

		double tempValue = value - min;
		double ratio = tempValue / diff;

		return (int) (ratio * nrValues);
	}

}
