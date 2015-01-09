package BookCircle;

//Java Bean Class for the event characteristics
import java.sql.Date;



public class event {
static String name;

public static String getName() {
	return name;
}
public static void setName(String name) {
	event.name = name;
}


public static String getLoc() {
	return loc;
}
public static void setLoc(String loc) {
	event.loc = loc;
}
public static String getDes() {
	return des;
}
public static void setDes(String des) {
	event.des = des;
}
static Date date;

public static Date getDate() {
	return date;
}
public static void setDate(Date date) {
	event.date = date;
}
static String loc;
static String des;
static String time;
static int eventID;
public static int getEventID() {
	return eventID;
}
public static void setEventID(int eventID) {
	event.eventID = eventID;
}
public static String getTime() {
	return time;
}
public static void setTime(String time) {
	event.time = time;
}
static int userID;
public static int getUserID() {
	return userID;
}
public static void setUserID(int userID) {
	event.userID = userID;
}

}
