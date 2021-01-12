package com.williamspreitzer.chess.network.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "login", "id", "node_id", "avatar_url", "gravatar_id", "url", "html_url", "followers",
		"following_url", "gists_url", "starred_url", "subscriptions_url", "organizations_url", "repos_url",
		"received_events_url", "type", "site_admin" })
public class Uploader {

	@JsonProperty("login")
	private String login;
	@JsonProperty("id")
	private int id;
	@JsonProperty("node_id")
	private String nodeId;
	@JsonProperty("avatar_url")
	private String avatarUrl;
	@JsonProperty("gravatar_id")
	private String gravatarId;
	@JsonProperty("url")
	private String url;
	@JsonProperty("html_url")
	private String htmlUrl;
	@JsonProperty("followers")
	private String followers;
	@JsonProperty("following_url")
	private String followingUrl;
	@JsonProperty("gists_url")
	private String gistsUrl;
	@JsonProperty("starred_url")
	private String starredUrl;
	@JsonProperty("subscriptions_url")
	private String subscriptionsUrl;
	@JsonProperty("organizations_url")
	private String organizationsUrl;
	@JsonProperty("repos_url")
	private String reposUrl;
	@JsonProperty("received_events_url")
	private String receivedEventsUrl;
	@JsonProperty("type")
	private String type;
	@JsonProperty("site_admin")
	private boolean siteAdmin;

	/**
	 * No args constructor for use in serialization
	 *
	 */
	public Uploader() {
	}

	/**
	 *
	 * @param receivedEventsUrl
	 * @param siteAdmin
	 * @param followingUrl
	 * @param gistsUrl
	 * @param avatarUrl
	 * @param organizationsUrl
	 * @param reposUrl
	 * @param htmlUrl
	 * @param subscriptionsUrl
	 * @param login
	 * @param type
	 * @param url
	 * @param starredUrl
	 * @param gravatarId
	 * @param followers
	 * @param id
	 * @param nodeId
	 */
	public Uploader(String login, int id, String nodeId, String avatarUrl, String gravatarId, String url,
			String htmlUrl, String followers, String followingUrl, String gistsUrl, String starredUrl,
			String subscriptionsUrl, String organizationsUrl, String reposUrl, String receivedEventsUrl, String type,
			boolean siteAdmin) {
		super();
		this.login = login;
		this.id = id;
		this.nodeId = nodeId;
		this.avatarUrl = avatarUrl;
		this.gravatarId = gravatarId;
		this.url = url;
		this.htmlUrl = htmlUrl;
		this.followers = followers;
		this.followingUrl = followingUrl;
		this.gistsUrl = gistsUrl;
		this.starredUrl = starredUrl;
		this.subscriptionsUrl = subscriptionsUrl;
		this.organizationsUrl = organizationsUrl;
		this.reposUrl = reposUrl;
		this.receivedEventsUrl = receivedEventsUrl;
		this.type = type;
		this.siteAdmin = siteAdmin;
	}

	@JsonProperty("login")
	public String getLogin() {
		return login;
	}

	@JsonProperty("login")
	public void setLogin(String login) {
		this.login = login;
	}

	@JsonProperty("id")
	public int getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(int id) {
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

	@JsonProperty("avatar_url")
	public String getAvatarUrl() {
		return avatarUrl;
	}

	@JsonProperty("avatar_url")
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	@JsonProperty("gravatar_id")
	public String getGravatarId() {
		return gravatarId;
	}

	@JsonProperty("gravatar_id")
	public void setGravatarId(String gravatarId) {
		this.gravatarId = gravatarId;
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

	@JsonProperty("followers")
	public String getFollowers() {
		return followers;
	}

	@JsonProperty("followers")
	public void setFollowers(String followers) {
		this.followers = followers;
	}

	@JsonProperty("following_url")
	public String getFollowingUrl() {
		return followingUrl;
	}

	@JsonProperty("following_url")
	public void setFollowingUrl(String followingUrl) {
		this.followingUrl = followingUrl;
	}

	@JsonProperty("gists_url")
	public String getGistsUrl() {
		return gistsUrl;
	}

	@JsonProperty("gists_url")
	public void setGistsUrl(String gistsUrl) {
		this.gistsUrl = gistsUrl;
	}

	@JsonProperty("starred_url")
	public String getStarredUrl() {
		return starredUrl;
	}

	@JsonProperty("starred_url")
	public void setStarredUrl(String starredUrl) {
		this.starredUrl = starredUrl;
	}

	@JsonProperty("subscriptions_url")
	public String getSubscriptionsUrl() {
		return subscriptionsUrl;
	}

	@JsonProperty("subscriptions_url")
	public void setSubscriptionsUrl(String subscriptionsUrl) {
		this.subscriptionsUrl = subscriptionsUrl;
	}

	@JsonProperty("organizations_url")
	public String getOrganizationsUrl() {
		return organizationsUrl;
	}

	@JsonProperty("organizations_url")
	public void setOrganizationsUrl(String organizationsUrl) {
		this.organizationsUrl = organizationsUrl;
	}

	@JsonProperty("repos_url")
	public String getReposUrl() {
		return reposUrl;
	}

	@JsonProperty("repos_url")
	public void setReposUrl(String reposUrl) {
		this.reposUrl = reposUrl;
	}

	@JsonProperty("received_events_url")
	public String getReceivedEventsUrl() {
		return receivedEventsUrl;
	}

	@JsonProperty("received_events_url")
	public void setReceivedEventsUrl(String receivedEventsUrl) {
		this.receivedEventsUrl = receivedEventsUrl;
	}

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	@JsonProperty("site_admin")
	public boolean isSiteAdmin() {
		return siteAdmin;
	}

	@JsonProperty("site_admin")
	public void setSiteAdmin(boolean siteAdmin) {
		this.siteAdmin = siteAdmin;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("login", login).append("id", id).append("nodeId", nodeId)
				.append("avatarUrl", avatarUrl).append("gravatarId", gravatarId).append("url", url)
				.append("htmlUrl", htmlUrl).append("followers", followers).append("followingUrl", followingUrl)
				.append("gistsUrl", gistsUrl).append("starredUrl", starredUrl)
				.append("subscriptionsUrl", subscriptionsUrl).append("organizationsUrl", organizationsUrl)
				.append("reposUrl", reposUrl).append("receivedEventsUrl", receivedEventsUrl).append("type", type)
				.append("siteAdmin", siteAdmin).toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(receivedEventsUrl).append(siteAdmin).append(followingUrl).append(gistsUrl)
				.append(avatarUrl).append(organizationsUrl).append(reposUrl).append(htmlUrl).append(subscriptionsUrl)
				.append(login).append(type).append(url).append(starredUrl).append(gravatarId).append(followers)
				.append(id).append(nodeId).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Uploader) == false) {
			return false;
		}
		Uploader rhs = ((Uploader) other);
		return new EqualsBuilder().append(receivedEventsUrl, rhs.receivedEventsUrl).append(siteAdmin, rhs.siteAdmin)
				.append(followingUrl, rhs.followingUrl).append(gistsUrl, rhs.gistsUrl).append(avatarUrl, rhs.avatarUrl)
				.append(organizationsUrl, rhs.organizationsUrl).append(reposUrl, rhs.reposUrl)
				.append(htmlUrl, rhs.htmlUrl).append(subscriptionsUrl, rhs.subscriptionsUrl).append(login, rhs.login)
				.append(type, rhs.type).append(url, rhs.url).append(starredUrl, rhs.starredUrl)
				.append(gravatarId, rhs.gravatarId).append(followers, rhs.followers).append(id, rhs.id)
				.append(nodeId, rhs.nodeId).isEquals();
	}

}