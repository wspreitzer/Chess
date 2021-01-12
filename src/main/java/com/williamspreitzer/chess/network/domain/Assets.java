package com.williamspreitzer.chess.network.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "url", "browser_download_url", "id", "node_id", "name", "label", "state", "content_type", "size",
		"download_count", "created_at", "updated_at", "uploader" })
public class Assets {

	@JsonProperty("url")
	private String url;
	@JsonProperty("browser_download_url")
	private String browserDownloadUrl;
	@JsonProperty("id")
	private double id;
	@JsonProperty("node_id")
	private String nodeId;
	@JsonProperty("name")
	private String name;
	@JsonProperty("label")
	private String label;
	@JsonProperty("state")
	private String state;
	@JsonProperty("content_type")
	private String contentType;
	@JsonProperty("size")
	private double size;
	@JsonProperty("download_count")
	private double downloadCount;
	@JsonProperty("created_at")
	private Object createdAt;
	@JsonProperty("updated_at")
	private Object updatedAt;
	@JsonProperty("uploader")
	private Uploader uploader;

	/**
	 * No args constructor for use in serialization
	 *
	 */
	public Assets() {
	}

	/**
	 *
	 * @param label
	 * @param url
	 * @param createdAt
	 * @param size
	 * @param uploader
	 * @param name
	 * @param id
	 * @param state
	 * @param browserDownloadUrl
	 * @param nodeId
	 * @param contentType
	 * @param downloadCount
	 * @param updatedAt
	 */
	public Assets(String url, String browserDownloadUrl, double id, String nodeId, String name, String label,
			String state, String contentType, double size, double downloadCount, Object createdAt, Object updatedAt,
			Uploader uploader) {
		super();
		this.url = url;
		this.browserDownloadUrl = browserDownloadUrl;
		this.id = id;
		this.nodeId = nodeId;
		this.name = name;
		this.label = label;
		this.state = state;
		this.contentType = contentType;
		this.size = size;
		this.downloadCount = downloadCount;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.uploader = uploader;
	}

	@JsonProperty("url")
	public String getUrl() {
		return url;
	}

	@JsonProperty("url")
	public void setUrl(String url) {
		this.url = url;
	}

	@JsonProperty("browser_download_url")
	public String getBrowserDownloadUrl() {
		return browserDownloadUrl;
	}

	@JsonProperty("browser_download_url")
	public void setBrowserDownloadUrl(String browserDownloadUrl) {
		this.browserDownloadUrl = browserDownloadUrl;
	}

	@JsonProperty("id")
	public double getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(double id) {
		this.id = id;
	}

	@JsonProperty("node_id")
	public String getNodeId() {
		return nodeId;
	}

	@JsonProperty("node_id")
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("label")
	public String getLabel() {
		return label;
	}

	@JsonProperty("label")
	public void setLabel(String label) {
		this.label = label;
	}

	@JsonProperty("state")
	public String getState() {
		return state;
	}

	@JsonProperty("state")
	public void setState(String state) {
		this.state = state;
	}

	@JsonProperty("content_type")
	public String getContentType() {
		return contentType;
	}

	@JsonProperty("content_type")
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@JsonProperty("size")
	public double getSize() {
		return size;
	}

	@JsonProperty("size")
	public void setSize(double size) {
		this.size = size;
	}

	@JsonProperty("download_count")
	public double getDownloadCount() {
		return downloadCount;
	}

	@JsonProperty("download_count")
	public void setDownloadCount(double downloadCount) {
		this.downloadCount = downloadCount;
	}

	@JsonProperty("created_at")
	public Object getCreatedAt() {
		return createdAt;
	}

	@JsonProperty("created_at")
	public void setCreatedAt(Object createdAt) {
		this.createdAt = createdAt;
	}

	@JsonProperty("updated_at")
	public Object getUpdatedAt() {
		return updatedAt;
	}

	@JsonProperty("updated_at")
	public void setUpdatedAt(Object updatedAt) {
		this.updatedAt = updatedAt;
	}

	@JsonProperty("uploader")
	public Uploader getUploader() {
		return uploader;
	}

	@JsonProperty("uploader")
	public void setUploader(Uploader uploader) {
		this.uploader = uploader;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("url", url).append("browserDownloadUrl", browserDownloadUrl)
				.append("id", id).append("nodeId", nodeId).append("name", name).append("label", label)
				.append("state", state).append("contentType", contentType).append("size", size)
				.append("downloadCount", downloadCount).append("createdAt", createdAt).append("updatedAt", updatedAt)
				.append("uploader", uploader).toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(label).append(url).append(createdAt).append(size).append(uploader)
				.append(name).append(id).append(state).append(browserDownloadUrl).append(nodeId).append(contentType)
				.append(downloadCount).append(updatedAt).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Assets) == false) {
			return false;
		}
		Assets rhs = ((Assets) other);
		return new EqualsBuilder().append(label, rhs.label).append(url, rhs.url).append(createdAt, rhs.createdAt)
				.append(size, rhs.size).append(uploader, rhs.uploader).append(name, rhs.name).append(id, rhs.id)
				.append(state, rhs.state).append(browserDownloadUrl, rhs.browserDownloadUrl).append(nodeId, rhs.nodeId)
				.append(contentType, rhs.contentType).append(downloadCount, rhs.downloadCount)
				.append(updatedAt, rhs.updatedAt).isEquals();
	}

}