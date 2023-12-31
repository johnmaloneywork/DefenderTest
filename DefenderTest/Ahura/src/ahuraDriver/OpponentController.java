package ahuraDriver;

public class OpponentController {
    static double minDistOpponentSpeedReviser = 25.0;
    static double minDistOpponentSteerRevisr = 0.0;
    static double minBesideDistanceOvertake = 1.9;


    static double besideAlter = 0.2;

    static double carWidth = 5;
    static double minSteerOvertake = 0.5;
    static double minSteerSlowDown = 0.8;
    static double previousDistanceSpeed = -10;
    static double previousTimeSpeed = -10;
    static double previousDistanceSteer = -10;
    static double previousTimeSteer = -10;

    public static double opponentSpeedReviser(MySensorModel sensors, double targetSpeed, double estimatedTurn, double distInfront, double steer){
        if(sensors.getDamage() > 7000){
            carWidth = 6.0;
            minBesideDistanceOvertake = 2.5;
        }else{
            if(sensors.getSpeed() < 50.0){
                carWidth = 4.0;
            }else
                carWidth = 6.0;
        }

        double minDist = minSomeoneInfron(sensors, true);

        double rv = 0.0;
        if(previousDistanceSpeed < 0){
            previousDistanceSpeed = minDist;
            previousTimeSpeed = sensors.getCurrentLapTime();
        }else{
            //calculate relative speed
            double currentTime = sensors.getCurrentLapTime();
            double currentDist = minDist;
            rv = (currentDist - previousDistanceSpeed)/(currentTime - previousTimeSpeed);
            rv=-rv;

            previousTimeSpeed = sensors.getCurrentLapTime();
            previousDistanceSpeed = minDist;

        }
        double distToBrakeIn50 = 50.0;
        if(sensors.getDamage() > 7000)
            distToBrakeIn50 = 80.0;

        double distToStartbraking = ((distToBrakeIn50-7.0)/(50.0 - 0.0))*(rv)+7.0;

//		System.out.println(distToStartbraking + " " + minDist);

        if(minDist < distToStartbraking){

            if((estimatedTurn > minSteerSlowDown && distInfront < 20.0) || steer > minSteerSlowDown)//it is dangerous to slow down when you are turning, we are close to a turn
                return Math.min(sensors.getSpeed()*1.0, targetSpeed);


            double coef = 0.9;
            if(sensors.getDamage() > 7000)
                coef = 0.5;
//			coef = Math.min(coef, 0.98);
//			coef = Math.max(coef, 0.6);

            return Math.min(sensors.getSpeed()*coef, targetSpeed);
        }

        return targetSpeed;
    }

    public static double[] opponentSteerReviser(MySensorModel sensors, double s, double steer, double correctionSensors){
//		double [] opponentInfo = sensors.getOpponentSensors();
//		for(int i = 0; i < 36; ++i)
//			System.out.println(i + ": " + opponentInfo[i]);

        if(Math.abs(steer) > minSteerOvertake)//do not over take when you are turning intensely
            return new double [] {s, correctionSensors};

        double minDistInf = minSomeoneInfron(sensors, false);

//		if(minDistInf < minDistOpponentSteerRevisr){
//			return new double [] {s, correctionSensors};
//		}

        double rv = 0.0;
        if(previousDistanceSteer < 0){
            previousDistanceSteer = minDistInf;
            previousTimeSteer = sensors.getCurrentLapTime();
        }else{
            //calculate relative speed
            double currentTime = sensors.getCurrentLapTime();
            double currentDist = minDistInf;
            rv = (currentDist - previousDistanceSteer)/(currentTime - previousTimeSteer);
            rv=-rv;
            previousTimeSteer = sensors.getCurrentLapTime();
            previousDistanceSteer = minDistInf;
        }

        double distToStartOvertaking = ((30.0-10.0)/(20.0 - 0.0))*(rv)+10.0;
        int angBeside = maxSomeoneBeside(sensors);

        if(minDistInf > distToStartOvertaking || angBeside != 18){
            //if there is no one in next 25 meters, don't act, only take care of besides
//			System.out.println(angBeside);
            if(angBeside == 18){
                return new double [] {s, correctionSensors};
            }

            double pos = -sensors.getTrackPosition();

            if(angBeside > 18){
                pos -= besideAlter;
            }else{
                if(angBeside < 18){
                    pos += besideAlter;
                }
            }

//			System.out.println(pos + " " + sensors.getTrackPosition() + " " + angBeside);

            pos=Math.max(pos, -0.95);
            pos=Math.min(pos, 0.95);

            return new double [] {pos, 10};
        }

        int ang = maxSomeoneInfron(sensors);
//		System.out.println(ang);
        double direction = Math.abs((18 - ang)*10.0);
        double overTakeTurn = -sensors.getTrackPosition();

        double directionAlt = direction/(((100.0-1000.0)/(20.0 - 0.0))*(rv)+1000.0);

        if(minDistInf < 15.0){
            directionAlt = Math.max(0.4, directionAlt);
        }

        if(ang > 18){
            overTakeTurn += directionAlt;
        }else{
            if(ang < 18)
                overTakeTurn -= directionAlt;
        }
//		double overTakeTurn = ((0.0-1.0)/(minDistOpponentSpeedReviser-reviserDist))*(minDistInf - reviserDist)+1.0;
        overTakeTurn = Math.min(0.95, overTakeTurn);
        overTakeTurn = Math.max(-0.95, overTakeTurn);

//		System.out.format("%.2f %.2f %.2f %.2f %d %.2f\n", minDistInf, directionAlt, sensors.getTrackPosition(), overTakeTurn, ang, rv);
//		if(ang<18){//at the left
//			overTakeTurn = -overTakeTurn;
//		}

//		System.out.println(steer + " " + overTakeTurn + " " + s);
//		double res = overTakeTurn + s;
//		System.out.println(s + " " + overTakeTurn + " " + ang);
//		return res;
        //revise based on besides
//		int angBeside = maxSomeoneBeside(sensors);
//		System.out.println(angBeside);
//		if(angBeside == 18)
        return new double [] {overTakeTurn, 10.0};
//		return -1.0;
//		if(angBeside < 18){
//			//right hand side there is someone who is less that 1 meter close
//			if(res < 0){
//				//we are going to go to the right for overtaking
//				return s;//dont overtake!
//			}else
//				return res;
//		}else{
////			//left hand side there is someone who is less that 1 meter close
//			if(res > 0){
////				//we are going to go to the left for overtaking
//				return s;//dont overtake!
//			}else
//				return res;
//		}
//		double [] bestDirection = maxSomeoneInfron(sensors);

//		System.out.println(bestDirection[0] + " " + bestDirection[1] + " " + overTakeTurn);


//		return steer - Math.signum((18-bestDirection[1]))*overTakeTurn;

    }

    private static double minSomeoneInfron(MySensorModel sensors, boolean exact){
        double [] opponentInfo = sensors.getOpponentSensors();
        double minDist = 100000.0;
        for(int i = 15; i<=21; ++i){
//			if(opponentInfo[i] > 25)
//				continue;

            double width = Math.abs(DriverControllerHelperE6.sinAngO[i]*opponentInfo[i]);

            if(width < carWidth/2.0){//it is infront of us

                double distance = Math.abs(DriverControllerHelperE6.cosAngO[i]*opponentInfo[i]);
//				System.out.println((18-i)*10.0 + " " + width + " " + distance);
                if(distance < minDist)
                    minDist = distance;
            }else{
                if(!exact){
                    double diff = width - minBesideDistanceOvertake - carWidth/2.0;
                    double c = ((0.0 - 1.0)/(1.0 - 0.0))*(diff - 0.0) + 1.0;
                    c = Math.max(0.0, c);
                    c = Math.min(1.0, c);

                    double distance = Math.abs(DriverControllerHelperE6.cosAngO[i]*opponentInfo[i])*(1.0-c)*5.0;
                    //				System.out.println((18-i)*10.0 + " " + width + " " + distance);
                    if(distance < minDist)
                        minDist = distance;
                }
            }
        }

        return minDist;
    }

    private static int maxSomeoneInfron(MySensorModel sensors){
        double [] opponentInfo = sensors.getOpponentSensors();
        int ang = 18;
        int finalAng = 18;

//		for(int i = 0; i<opponentInfo.length; ++i){
//			for(int j = 0; j < DriverControllerHelperE6.angles.length; ++j){
//				if(Math.abs(DriverControllerHelperE6.angles[j] - ((double)(i - 18)*10.0)) < 1.0){
//					opponentInfo[i] = Math.min(sensors.getTrackEdgeSensors()[j], opponentInfo[i]);
//				}
//			}
//		}
//
        double avr = (opponentInfo[ang] + opponentInfo[ang + 1] + opponentInfo[ang - 1])/3.0;
        double width = Math.abs(avr);

        for(int i = 0; i<=8; ++i){
            int j = (int) Math.ceil((double) i/2.0);
            j *= ((i%2)==0 ? 1 : -1);

            avr = (opponentInfo[j + ang] + opponentInfo[j + ang + 1] + opponentInfo[j + ang - 1])/3.0;
//			System.out.println((ang+j) + ": " +opponentInfo[j + ang] + " " + opponentInfo[j + ang + 1] + " " + opponentInfo[j + ang - 1] + " " + Math.abs(avr));

            if(width < Math.abs(avr)){
                finalAng = ang + j;
                width = Math.abs(avr);
            }
//			if(width > 40.0 && opponentInfo[j + ang] > 40.0 && opponentInfo[j + ang + 1] > 40.0 && opponentInfo[j + ang - 1] > 40.0)
//				break;


//			System.out.println((18-i)*10.0 + " " + width + " " + distance);


        }
//		System.out.println(finalAng);
//		System.out.println();
//		System.out.println("**********************");
        return finalAng;
    }

    private static int maxSomeoneBeside(MySensorModel sensors){
        double [] opponentInfo = sensors.getOpponentSensors();
        int ang = 18;
        int finalAng = 18;
        for(int i = 10; i<=30; ++i){
            int j = (int) Math.ceil((double) i/2.0);
            j *= ((i%2)==0 ? 1 : -1);
            double avr = (opponentInfo[j + ang]);

            double width = Math.abs(avr);

//			System.out.println(width + " " + (j + ang));
            if(width < minBesideDistanceOvertake + carWidth/2.0){
                finalAng = ang + j;
                break;
            }
//			System.out.println((18-i)*10.0 + " " + width + " " + distance);


        }
//		System.out.println("***************************");
        return finalAng;
    }


}
