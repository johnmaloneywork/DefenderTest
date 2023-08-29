package ahuraDriver;

import java.util.ArrayList;
import java.util.List;


/**
 * @author mbonyadi
 *
 */
public class DriverControllerHelperE6 {

//	public static List<String> parametersNames = Arrays.asList("Speed Slope", "Speed Shift", "Minimum Soar", "Maximum Soar");
//	public static List<Double> parametersValues = Arrays.asList(new Double [] {-0.045,-60.0, 128.0, 190.0});//Arrays.asList();
//	public static List<Double> parametersValues = Arrays.asList(new Double [] {-0.0386701, -60.47302, 115.197312, 145.494482});//Wheel 2
//	public static List<Double> parametersValues = Arrays.asList(new Double [] {-0.034325809, -63.260505, 135.658577812, 156.9079397});//alpine 1

//	public static List<String> parametersNames = Arrays.asList("minAggC", "maxAggC", "minAggV", "maxAggV", "Minimum Soar", "Maximum Soar", "Min sensors", "Max sensors");
//	public static List<Double> parametersValues = Arrays.asList(new Double [] {0.78, 0.5, 30.0, 73.0, 100.0, 190.0, 1.0, 10.0});//Default
//	public static List<Double> parametersValues = Arrays.asList(new Double [] {0.751306400318202, 0.64415891163038, 36.3582555334631, 85.4166303527724, 113.017540043282, 195.699494515522, 2.885480805335156, 11.0485699383691});//Alpin 2 new
//	public static List<Double> parametersValues = Arrays.asList(new Double [] {0.78, 0.5, 30.0, 73.0, 100.0, 190.0, 1.0, 10.0});//Default
//	public static List<Double> parametersValues = Arrays.asList(new Double [] {0.6866457559152911, 0.4975002783948544, 53.402433212397256, 63.37957061916983, 82.39090988426344, 201.88395832807404, 2.2317339374220695, 12.070598958186247});//Wheel-2 new
//	public static List<Double> parametersValues = Arrays.asList(new Double [] {25.0, 1.0, .05, .000005, 100.0, 190.0, 1.0});//Default2
//	public static List<Double> parametersValues = Arrays.asList(new Double [] {0.6811289315556591, 0.5248083762507209, 6.701300307587797, 71.76292280652007, 124.9762126037406, 198.63111512384128});//Optimized for GCSpeedway 1
//	public static List<Double> parametersValues = Arrays.asList(new Double [] {0.7026262670644499, 0.5842304987957718, 16.482456268211614, 79.7845469667692, 101.45794818695263, 204.23110052812757});//Optimized for Wheel1
//	public static List<Double> parametersValues = Arrays.asList(new Double [] {0.688749700751826, 0.515510611802827, 43.8001118701033, 78.2204811649894, 136.781624932788, 186.669845511425, 3.5436245182342});//Optimized for wheel2
//	public static List<Double> parametersValues = Arrays.asList(new Double [] {0.6111889488876314, 0.4058498838987334, 27.434474267619027, 98.93284571516581, 104.5886341652808, 219.59133357143222});//Optimized wheel2 10 damage
//	public static List<Double> parametersValues = Arrays.asList(new Double [] {0.5961866376374299, 0.5386892746314815, 17.174712804852295, 83.84132856451929, 102.94546484223423, 224.23832215146285});//Optimized for Froza
//	public static List<Double> parametersValues = Arrays.asList(new Double [] {0.6691935105310137, 0.7449380220609059, 13.748318371237282, 73.41263467159703, 111.98369350715802 , 217.58908343342264});//Optimized for Alpine2
//	public static List<Double> parametersValues = Arrays.asList(new Double []{0.6781902125919642, 0.4953233631206883, 58.84968402648872, 60.44019016167788, 133.25805193822336, 182.3068697036016, 1.0});

    /**Ahura Specific Code Start*/
    public static int memorySensorLength = 1;
    /**Ahura Specific Code End*/

    public static int[]  gearUp={9500,9400,9500,9500,9500,0};
    public static int[]  gearDown={0,3300,6200,7000,7300,7700};

    public static double maxSpeed = 360;

    /**Ahura Specific Code Start*/
    public static double minSpeed = 25;
    public static double maxSensorRangeProximity = 200;
    public static List<Double> previousAngles = new ArrayList<Double>();
    public static int maxSizeAnglesTrack = 10;

//	public static double boundaryOfTheTrack = 0.95;
//	public static double aggressionSteer = .1;
//	public static double aggressionSpeed = 0.75;	
    //Forza 0.65: 101 average, 99 best

    //	public static float [] angles = {-90,-75,-60,-45,-30,-20,-15,-10,-5,0,5,10,15,20,30,45,60,75,90};
//	public static float [] angleHelpers = {-1, 1};
    public static float [] angles = {-90,-75,-50,-35,-20,-15,-10,-5,-1,0,1,5,10,15,20,35,50,75,90};

    public static double [] cosAng = {6.12323399573677e-17,0.258819045102521,0.642787609686539,0.819152044288992,0.939692620785908,0.965925826289068,0.984807753012208,0.996194698091746,0.999847695156391,1,0.999847695156391,0.996194698091746,0.984807753012208,0.965925826289068,0.939692620785908,0.819152044288992,0.642787609686539,0.258819045102521,6.12323399573677e-17};
//	{Math.cos(-90*Math.PI/180),Math.cos(-75*Math.PI/180),Math.cos(-50*Math.PI/180),Math.cos(-35*Math.PI/180),Math.cos(-20*Math.PI/180),Math.cos(-15*Math.PI/180)
//		,Math.cos(-10*Math.PI/180),Math.cos(-5*Math.PI/180),Math.cos(-1*Math.PI/180),Math.cos(0*Math.PI/180),Math.cos(1*Math.PI/180),Math.cos(5*Math.PI/180),Math.cos(10*Math.PI/180)
//		,Math.cos(15*Math.PI/180),Math.cos(20*Math.PI/180),Math.cos(35*Math.PI/180),Math.cos(50*Math.PI/180),Math.cos(75*Math.PI/180),Math.cos(90*Math.PI/180)};
//	

    public static double [] sinAng = {-1,-0.965925826289068,-0.766044443118978,-0.573576436351046,-0.342020143325669,-0.258819045102521,-0.173648177666930,-0.0871557427476582,-0.0174524064372835,0,0.0174524064372835,0.0871557427476582,0.173648177666930,0.258819045102521,0.342020143325669,0.573576436351046,0.766044443118978,0.965925826289068,1};

//		{Math.sin(-90*Math.PI/180),Math.sin(-75*Math.PI/180),Math.sin(-50*Math.PI/180),Math.sin(-35*Math.PI/180),Math.sin(-20*Math.PI/180),Math.sin(-15*Math.PI/180)
//		,Math.sin(-10*Math.PI/180),Math.sin(-5*Math.PI/180),Math.sin(-1*Math.PI/180),Math.sin(0*Math.PI/180),Math.sin(1*Math.PI/180),Math.sin(5*Math.PI/180),Math.sin(10*Math.PI/180)
//		,Math.sin(15*Math.PI/180),Math.sin(20*Math.PI/180),Math.sin(35*Math.PI/180),Math.sin(50*Math.PI/180),Math.sin(75*Math.PI/180),Math.sin(90*Math.PI/180)};


    public static double [] sinAngO = {1.22464679914735e-16,0.173648177666930,0.342020143325669,0.500000000000000,0.642787609686540,0.766044443118978,0.866025403784439,0.939692620785908,0.984807753012208,1,0.984807753012208,0.939692620785908,0.866025403784439,0.766044443118978,0.642787609686539,0.500000000000000,0.342020143325669,0.173648177666930,0,-0.173648177666930,-0.342020143325669,-0.500000000000000,-0.642787609686539,-0.766044443118978,-0.866025403784439,-0.939692620785908,-0.984807753012208,-1,-0.984807753012208,-0.939692620785908,-0.866025403784439,-0.766044443118978,-0.642787609686540,-0.500000000000000,-0.342020143325669,-0.173648177666930,-1.22464679914735e-16};

    //		{Math.sin(180.0*Math.PI/180.0), Math.sin(170.0*Math.PI/180.0), Math.sin(160.0*Math.PI/180.0), Math.sin(150.0*Math.PI/180.0), Math.sin(140.0*Math.PI/180.0),
//		Math.sin(130.0*Math.PI/180.0), Math.sin(120.0*Math.PI/180.0), Math.sin(110.0*Math.PI/180.0), Math.sin(100.0*Math.PI/180.0), Math.sin(90.0*Math.PI/180.0), Math.sin(80.0*Math.PI/180.0),
//		Math.sin(70.0*Math.PI/180.0), Math.sin(60.0*Math.PI/180.0), Math.sin(50.0*Math.PI/180.0), Math.sin(40.0*Math.PI/180.0), Math.sin(30.0*Math.PI/180.0), Math.sin(20.0*Math.PI/180.0),
//		Math.sin(10.0*Math.PI/180.0), Math.sin(0.0*Math.PI/180.0), Math.sin(-10.0*Math.PI/180.0), Math.sin(-20.0*Math.PI/180.0), Math.sin(-30.0*Math.PI/180.0), Math.sin(-40.0*Math.PI/180.0),
//		Math.sin(-50.0*Math.PI/180.0), Math.sin(-60.0*Math.PI/180.0), Math.sin(-70.0*Math.PI/180.0), Math.sin(-80.0*Math.PI/180.0), Math.sin(-90.0*Math.PI/180.0), Math.sin(-100.0*Math.PI/180.0),
//		Math.sin(-110.0*Math.PI/180.0), Math.sin(-120.0*Math.PI/180.0), Math.sin(-130.0*Math.PI/180.0), Math.sin(-140.0*Math.PI/180.0), Math.sin(-150.0*Math.PI/180.0), Math.sin(-160.0*Math.PI/180.0),
//		Math.sin(-170.0*Math.PI/180.0), Math.sin(-180.0*Math.PI/180.0)};
//	
    public static double [] cosAngO = {-1,-0.984807753012208,-0.939692620785908,-0.866025403784439,-0.766044443118978,-0.642787609686539,-0.500000000000000,-0.342020143325669,-0.173648177666930,6.12323399573677e-17,0.173648177666930,0.342020143325669,0.500000000000000,0.642787609686539,0.766044443118978,0.866025403784439,0.939692620785908,0.984807753012208,1,0.984807753012208,0.939692620785908,0.866025403784439,0.766044443118978,0.642787609686539,0.500000000000000,0.342020143325669,0.173648177666930,6.12323399573677e-17,-0.173648177666930,-0.342020143325669,-0.500000000000000,-0.642787609686539,-0.766044443118978,-0.866025403784439,-0.939692620785908,-0.984807753012208,-1};
//	{Math.cos(180.0*Math.PI/180.0), Math.cos(170.0*Math.PI/180.0), Math.cos(160.0*Math.PI/180.0), Math.cos(150.0*Math.PI/180.0), Math.cos(140.0*Math.PI/180.0), 
//		Math.cos(130.0*Math.PI/180.0), Math.cos(120.0*Math.PI/180.0), Math.cos(110.0*Math.PI/180.0), Math.cos(100.0*Math.PI/180.0), Math.cos(90.0*Math.PI/180.0), Math.cos(80.0*Math.PI/180.0),
//		Math.cos(70.0*Math.PI/180.0), Math.cos(60.0*Math.PI/180.0), Math.cos(50.0*Math.PI/180.0), Math.cos(40.0*Math.PI/180.0), Math.cos(30.0*Math.PI/180.0), Math.cos(20.0*Math.PI/180.0),
//		Math.cos(10.0*Math.PI/180.0), Math.cos(0.0*Math.PI/180.0), Math.cos(-10.0*Math.PI/180.0), Math.cos(-20.0*Math.PI/180.0), Math.cos(-30.0*Math.PI/180.0), Math.cos(-40.0*Math.PI/180.0),
//		Math.cos(-50.0*Math.PI/180.0), Math.cos(-60.0*Math.PI/180.0), Math.cos(-70.0*Math.PI/180.0), Math.cos(-80.0*Math.PI/180.0), Math.cos(-90.0*Math.PI/180.0), Math.cos(-100.0*Math.PI/180.0),
//		Math.cos(-110.0*Math.PI/180.0), Math.cos(-120.0*Math.PI/180.0), Math.cos(-130.0*Math.PI/180.0), Math.cos(-140.0*Math.PI/180.0), Math.cos(-150.0*Math.PI/180.0), Math.cos(-160.0*Math.PI/180.0),
//		Math.cos(-170.0*Math.PI/180.0), Math.cos(-180.0*Math.PI/180.0)};
//	

    //	public static double [] cosAngO = {};
//	public static float [] angles = {-90,-75,-60,-50,-35,-20,-15,-10,-5,0,5,10,15,20,35,50,60,75,90};
    public static int zeroAngle = 9;

    public static double asrSlip= 1.0;
    public static double asrRange= 1.0;
    public static double asrMinSpeed = 150.0;
    /**Ahura Specific Code End*/

    /* ABS Filter Constants */
    public static float wheelRadius[]={(float) 0.3179,(float) 0.3179,(float) 0.3276,(float) 0.3276};
//	public static float absSlip=(float) 2.0;
//	public static float absRange=(float) 3.0;
//	public static float absMinSpeed=(float) 3.0;

    /* Steering constants*/
    public static float steerLock=(float) 0.785398;
    public static float steerSensitivityOffset=(float) 80.0;
    public static float wheelSensitivityCoeff=1;

    /**Ahura Specific Code Start*/
    public static double steeringMinimumTurn = 0.01;
    /**Ahura Specific Code End*/

    /* Clutching Constants */
    public static float clutchMax=(float) 0.5;
    public static float clutchDelta=(float) 0.05;
    public static float clutchRange=(float) 0.82;
    public static float	clutchDeltaTime=(float) 0.02;
    public static float clutchDeltaRaced=10;
    public static float clutchDec=(float) 0.01;
    public static float clutchMaxModifier=(float) 1.3;
    public static float clutchMaxTime=(float) 1.5;

    /**Ahura Specific Code Start*/

    private static double sin1 = Math.sin(Math.PI/180.0);
    private static double cos1 = Math.cos(Math.PI/180.0);

    public static int externumIndexAngle(double [] in, externumTypes type){
        int indx = 0;
        for (int i=0;i<in.length;++i){

            if(((double)externumTypes.toInt(type))*in[indx]>((double)externumTypes.toInt(type))*in[i]){
                indx = i;
            }else{
                if(((double)externumTypes.toInt(type))*in[indx]==((double)externumTypes.toInt(type))*in[i]){
                    if(Math.abs(angles[indx]) > Math.abs(angles[i]))
                        indx = i;
                }
            }
//			System.out.println(in[i]);
        }
//		System.out.println();
        if(Math.abs(angles[indx]) < 2)
            indx = zeroAngle;
        return indx;
    }


    public static double degreeToRadian(double in){
        return in*Math.PI/180.0;
    }

    public static double radianToDegree(double in){
        return in*180.0/Math.PI;
    }

    public static double maximumDistanceInfront(double[] proximities){
        return proximities[zeroAngle];
    }

    public static void addToAngles(double angle){
        if(previousAngles.size() > maxSizeAnglesTrack){
            previousAngles.remove(0);
        }
        previousAngles.add(angle);
    }





    /**
     * @param minY: minimum value that the curve can converge to
     * @param maxY: maximum value that the curve can converge to
     * @param minX: the value of x that has the minimumY
     * @param maxX: the value of x that has the maximumY
     * @param x: the current x that it y is needed
     * @return
     */
    public static double logSig(double minY,  double maxY, double minX, double maxX, double percent, double x){
//		double dist = maxY - minY;
//		double alpha=((Math.abs(dist)/(Math.abs(dist)-0.0001))-1.0);
//		double center = (maxX+minX)/2.0;
//		double coef = Math.log(alpha)/(maxX-center);
//		double res = ((dist)/(1+Math.exp((x-center)*coef))+minY);
//		res = Math.round(res*1000.0)/1000.0;
        if(minY>maxY){
            double t = maxY;
            maxY=minY;
            minY=t;
            t=maxX;
            maxX=minX;
            minX=t;
        }
        double c = Math.log(((maxY-minY/percent)*(percent*maxY-minY))/((-minY+minY/percent)*(maxY-percent*maxY)))/(minX-maxX);
        double d = -maxX+Math.log(((maxY-minY)/(percent*maxY-minY))-1.0)/c;
//		System.out.println(c+" "+d);
        double res= logSig(maxY, minY, c, d, x);
//				((maxY-minY)/(1+Math.exp((x+d)*c))+minY);
        return res;
    }

    public static double logSig(double maxY,  double minY, double c, double d, double x){
        double a= maxY-minY;
        double b = 1.0;
        double res = ((a)/(b+Math.exp(c*(x+d)))+minY);
//		System.out.println(res);
        res = Math.round(res*1000.0)/1000.0;
//		System.out.println(res);
//		double res=0.0;
        return res;
    }

    public static double angleToDistanceInterpolate(double angle, double[] dist){
        int i = 0;
        for(i=0;i<angles.length; ++i){
            if(angle<=angles[i]){
                break;
            }
        }


        if(angle == angles[i] || i <= 0 || i >= angles.length - 1)
            return dist[i];

        double al = dist[i-1];
        double ar = dist[i+1];
        double ac = dist[i];
        double a = (al+ar)/50.0;
        double c = ac;
        double b = (a*25.0-al)/5.0;

        return (a*angle*angle+b*angle+c);

    }

    public static double trapezoid(double a, double b, double c, double d, double inp){
        if(inp < a || inp > d){
            return 0.0;
        }

        if(inp > b && inp < c){
            return 1.0;
        }

        if(inp > a && inp < b){
            return (((1.0-0.0)/(b-a))*(inp-a));
        }

        return ((0.0-1.0)/(d-c))*(inp-c)+1.0;
    }

    public static double bell(double a, double b, double c, double inp){
        return ((1.0)/(1.0+(Math.abs(Math.pow((inp-c)/a, 2.0*b)))));
    }

    public static double turnDirectionCalculator(MySensorModel sensors,
                                                 int maxDistanceSensorIndx) {
//		double baseAngl = DriverControllerHelperE6.angles[DriverControllerHelperE6.zeroAngle];

        double distLeft = sensors.getTrackEdgeSensors()[DriverControllerHelperE6.zeroAngle - 1];//DriverControllerHelperE4.angleToDistanceInterpolate(leftAngl, sensors.getTrackEdgeSensors());
        double distRight = sensors.getTrackEdgeSensors()[DriverControllerHelperE6.zeroAngle + 1];//DriverControllerHelperE4.angleToDistanceInterpolate(rightAngl, sensors.getTrackEdgeSensors());
        double distBase = sensors.getTrackEdgeSensors()[DriverControllerHelperE6.zeroAngle];//DriverControllerHelperE4.angleToDistanceInterpolate(baseAngl, sensors.getTrackEdgeSensors());
        double sinAngle = 0.0;
        double k = 0.0;

        if(distRight > distLeft){
            k = distRight*sin1/(distBase-distRight*cos1);
        }else{
            k = distBase*sin1/(distLeft-distBase*cos1);
        }
        sinAngle = Math.atan(k);

//		sinAngle *= (180.0/Math.PI);
//		System.out.format("res: %.2f, left: %.2f, base: %.2f, right: %.2f, k: %.2f\n", sinAngle, distLeft, distBase, distRight, k);		
        return sinAngle;


//		if(distLeft - distBase > 5 && distBase - distRight > 5){
//			turnDirection = -1.0;
//		}
//		
//		if(distRight - distBase > 5 && distBase - distLeft > 5){
//			turnDirection = 1.0;
//		}
//		
//		turnDirections.add(turnDirection);
//		if(turnDirections.size()>10){
//			turnDirections.remove(0);
//		}
//		return turnDirection;
    }

    public static boolean isTowardsInsideTheTrack(MySensorModel sensors){
        return sensors.getTrackPosition()*sensors.getAngleToTrackAxis() > 0;
    }
    public static boolean isOnTheLeftHandSide(MySensorModel sensors){
        return Math.abs(sensors.getTrackPosition()) > 0.0 ? sensors.getTrackPosition() > 0 : false;
    }
    public static boolean isInTheCorrectDirection(MySensorModel sensor){
        return Math.abs(sensor.getAngleToTrackAxis()) <= Math.PI/2;
    }

    /**Ahura Specific Code End*/
}