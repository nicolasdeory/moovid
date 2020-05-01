package aiss.model.photos.filter;

import com.google.appengine.repackaged.com.google.type.Date;

public class DateFilter{
	
	private Date[] dates;
	private DateRange[] ranges;
	
	public DateFilter(Date[] dates, DateRange[] ranges) {
		super();
		this.dates = dates;
		this.ranges = ranges;
	}
	public Date[] getDates() {
		return dates;
	}
	public void setDates(Date[] dates) {
		this.dates = dates;
	}
	public DateRange[] getRanges() {
		return ranges;
	}
	public void setRanges(DateRange[] ranges) {
		this.ranges = ranges;
	}
	
	
	
}