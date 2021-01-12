package com.williamspreitzer.chess.network.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "url", "html_url", "assets_url", "upload_url", "tarball_url", "zipball_url", "id", "node_id",
		"tag_name", "target_commitish", "name", "body", "draft", "prerelease", "created_at", "publish_at", "author" })
public class Latest {

	@JsonProperty("url")
	private String url;
	@JsonProperty("html_url")
	private String htmlUrl;
	@JsonProperty("assets_url")
	private String assetsUrl;
	@JsonProperty("upload_url")
	private String uploadUrl;
	@JsonProperty("tarball_url")
	private String tarballUrl;
	@JsonProperty("zipball_url")
	private String zipballUrl;
	@JsonProperty("id")
	private String id;
	@JsonProperty("node_id")
	private String nodeId;
	@JsonProperty("tag_name")
	private String tagName;
	@JsonProperty("target_commitish")
	private String targetCommitish;
	@JsonProperty("name")
	private int name;
	@JsonProperty("body")
	private String body;
	@JsonProperty("draft")
	private String draft;
	@JsonProperty("prerelease")
	private boolean prerelease;
	@JsonProperty("created_at")
	private Object createdAt;
	@JsonProperty("publish_at")
	private Object publishAt;
	@JsonProperty("author")
	private Author author;

	/**
	 * No args constructor for use in serialization
	 *
	 */
	public Latest() {
	}

	/**
	 *
	 * @param tarballUrl
	 * @param author
	 * @param publishAt
	 * @param htmlUrl
	 * @param tagName
	 * @param targetCommitish
	 * @param body
	 * @param zipballUrl
	 * @param url
	 * @param createdAt
	 * @param uploadUrl
	 * @param prerelease
	 * @param draft
	 * @param name
	 * @param id
	 * @param nodeId
	 * @param assetsUrl
	 */
	public Latest(String url, String htmlUrl, String assetsUrl, String uploadUrl, String tarballUrl, String zipballUrl,
			String id, String nodeId, String tagName, String targetCommitish, int name, String body, String draft,
			boolean prerelease, Object createdAt, Object publishAt, Author author) {
		super();
		this.url = url;
		this.htmlUrl = htmlUrl;
		this.assetsUrl = assetsUrl;
		this.uploadUrl = uploadUrl;
		this.tarballUrl = tarballUrl;
		this.zipballUrl = zipballUrl;
		this.id = id;
		this.nodeId = nodeId;
		this.tagName = tagName;
		this.targetCommitish = targetCommitish;
		this.name = name;
		this.body = body;
		this.draft = draft;
		this.prerelease = prerelease;
		this.createdAt = createdAt;
		this.publishAt = publishAt;
		this.author = author;
	}

	@JsonProperty("url")
	public String getUrl() {
		return url;
	}

	@JsonProperty("url")
	public void setUrl(String url) {
		this.url = url;
	}

	@JsonProperty("html_url")
	public String getHtmlUrl() {
		return htmlUrl;
	}

	@JsonProperty("html_url")
	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
	}

	@JsonProperty("assets_url")
	public String getAssetsUrl() {
		return assetsUrl;
	}

	@JsonProperty("assets_url")
	public void setAssetsUrl(String assetsUrl) {
		this.assetsUrl = assetsUrl;
	}

	@JsonProperty("upload_url")
	public String getUploadUrl() {
		return uploadUrl;
	}

	@JsonProperty("upload_url")
	public void setUploadUrl(String uploadUrl) {
		this.uploadUrl = uploadUrl;
	}

	@JsonProperty("tarball_url")
	public String getTarballUrl() {
		return tarballUrl;
	}

	@JsonProperty("tarball_url")
	public void setTarballUrl(String tarballUrl) {
		this.tarballUrl = tarballUrl;
	}

	@JsonProperty("zipball_url")
	public String getZipballUrl() {
		return zipballUrl;
	}

	@JsonProperty("zipball_url")
	public void setZipballUrl(String zipballUrl) {
		this.zipballUrl = zipballUrl;
	}

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
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

	@JsonProperty("tag_name")
	public String getTagName() {
		return tagName;
	}

	@JsonProperty("tag_name")
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	@JsonProperty("target_commitish")
	public String getTargetCommitish() {
		return targetCommitish;
	}

	@JsonProperty("target_commitish")
	public void setTargetCommitish(String targetCommitish) {
		this.targetCommitish = targetCommitish;
	}

	@JsonProperty("name")
	public int getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(int name) {
		this.name = name;
	}

	@JsonProperty("body")
	public String getBody() {
		return body;
	}

	@JsonProperty("body")
	public void setBody(String body) {
		this.body = body;
	}

	@JsonProperty("draft")
	public String getDraft() {
		return draft;
	}

	@JsonProperty("draft")
	public void setDraft(String draft) {
		this.draft = draft;
	}

	@JsonProperty("prerelease")
	public boolean isPrerelease() {
		return prerelease;
	}

	@JsonProperty("prerelease")
	public void setPrerelease(boolean prerelease) {
		this.prerelease = prerelease;
	}

	@JsonProperty("created_at")
	public Object getCreatedAt() {
		return createdAt;
	}

	@JsonProperty("created_at")
	public void setCreatedAt(Object createdAt) {
		this.createdAt = createdAt;
	}

	@JsonProperty("publish_at")
	public Object getPublishAt() {
		return publishAt;
	}

	@JsonProperty("publish_at")
	public void setPublishAt(Object publishAt) {
		this.publishAt = publishAt;
	}

	@JsonProperty("author")
	public Author getAuthor() {
		return author;
	}

	@JsonProperty("author")
	public void setAuthor(Author author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("url", url).append("htmlUrl", htmlUrl).append("assetsUrl", assetsUrl)
				.append("uploadUrl", uploadUrl).append("tarballUrl", tarballUrl).append("zipballUrl", zipballUrl)
				.append("id", id).append("nodeId", nodeId).append("tagName", tagName)
				.append("targetCommitish", targetCommitish).append("name", name).append("body", body)
				.append("draft", draft).append("prerelease", prerelease).append("createdAt", createdAt)
				.append("publishAt", publishAt).append("author", author).toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(tarballUrl).append(author).append(publishAt).append(htmlUrl).append(tagName)
				.append(targetCommitish).append(body).append(zipballUrl).append(url).append(createdAt).append(uploadUrl)
				.append(prerelease).append(draft).append(name).append(id).append(nodeId).append(assetsUrl).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Latest) == false) {
			return false;
		}
		Latest rhs = ((Latest) other);
		return new EqualsBuilder().append(tarballUrl, rhs.tarballUrl).append(author, rhs.author)
				.append(publishAt, rhs.publishAt).append(htmlUrl, rhs.htmlUrl).append(tagName, rhs.tagName)
				.append(targetCommitish, rhs.targetCommitish).append(body, rhs.body).append(zipballUrl, rhs.zipballUrl)
				.append(url, rhs.url).append(createdAt, rhs.createdAt).append(uploadUrl, rhs.uploadUrl)
				.append(prerelease, rhs.prerelease).append(draft, rhs.draft).append(name, rhs.name).append(id, rhs.id)
				.append(nodeId, rhs.nodeId).append(assetsUrl, rhs.assetsUrl).isEquals();
	}

}