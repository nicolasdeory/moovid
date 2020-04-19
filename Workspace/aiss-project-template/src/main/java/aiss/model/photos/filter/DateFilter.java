package aiss.model.photos.filter;

import com.google.appengine.repackaged.com.google.type.Date;

public class DateFilter implements Filters{
	
	private Date[] dates;
	private DateRange[] ranges;
	private Boolean includedArchivedMedia;
	private Boolean excludeNonAppCreatedData;
	
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
	@Override
	public Boolean getIncludedArchivedMedia() {
		return includedArchivedMedia;
	}
	@Override
	public void setIncludedArchivedMedia(Boolean IncludedArchivedMedia) {
		this.includedArchivedMedia = IncludedArchivedMedia;
	}
	@Override
	public Boolean getExcludeNonAppCreatedData() {
		// TODO Auto-generated method stub
		return excludeNonAppCreatedData;
	}
	@Override
	public void setExcludeNonAppCreatedData(Boolean ExcludeNonAppCreatedData) {
		this.excludeNonAppCreatedData = ExcludeNonAppCreatedData;
		
	}
	
	
	
}