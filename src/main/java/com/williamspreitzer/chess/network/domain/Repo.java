package com.williamspreitzer.chess.network.domain;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "node", "name", "full_name", "private", "owner", "html_url", "description", "fork", "url",
		"forks_url", "keys_url", "collaborators_url", "teams_url", "hooks_url", "issue_events_url", "events_url",
		"assignees_url", "branches_url", "tags_url", "blobs_url", "git_tags_url", "git_refs_url", "trees_url",
		"statuses_url", "languages_url", "stargazers_url", "contributors_url", "subscribers_url", "subscription_url",
		"commits_url", "git_commits_url", "comments_url", "issue_comment_url", "contents_url", "compare_url",
		"merges_url", "archive_url", "downloads_url", "issues_url", "pulls_url", "milestones_url", "notifications_url",
		"labels_url", "releases_url", "deployments_url", "created_at", "updated_at", "pushed_at", "git_url", "ssh_url",
		"clone_url", "svn_url", "homepage", "size", "stargazers_count", "watchers_count", "language", "has_issues",
		"has_projects", "hasdownloads", "has_wiki", "has_pages", "forks_count", "mirror_url", "archived", "disabled",
		"open_issues_count", "license", "forks", "open_issues", "watchers", "default_branch", "temp_clone_token",
		"network_count", "subscribers_count" })
public class Repo {

	@JsonProperty("id")
	private String id;
	@JsonProperty("node")
	private String node;
	@JsonProperty("name")
	private String name;
	@JsonProperty("full_name")
	private String fullName;
	@JsonProperty("private")
	private boolean _private;
	@JsonProperty("owner")
	private Object owner;
	@JsonProperty("html_url")
	private String htmlUrl;
	@JsonProperty("description")
	private String description;
	@JsonProperty("fork")
	private boolean fork;
	@JsonProperty("url")
	private String url;
	@JsonProperty("forks_url")
	private String forksUrl;
	@JsonProperty("keys_url")
	private String keysUrl;
	@JsonProperty("collaborators_url")
	private String collaboratorsUrl;
	@JsonProperty("teams_url")
	private String teamsUrl;
	@JsonProperty("hooks_url")
	private String hooksUrl;
	@JsonProperty("issue_events_url")
	private String issueEventsUrl;
	@JsonProperty("events_url")
	private String eventsUrl;
	@JsonProperty("assignees_url")
	private String assigneesUrl;
	@JsonProperty("branches_url")
	private String branchesUrl;
	@JsonProperty("tags_url")
	private String tagsUrl;
	@JsonProperty("blobs_url")
	private String blobsUrl;
	@JsonProperty("git_tags_url")
	private String gitTagsUrl;
	@JsonProperty("git_refs_url")
	private String gitRefsUrl;
	@JsonProperty("trees_url")
	private String treesUrl;
	@JsonProperty("statuses_url")
	private String statusesUrl;
	@JsonProperty("languages_url")
	private String languagesUrl;
	@JsonProperty("stargazers_url")
	private String stargazersUrl;
	@JsonProperty("contributors_url")
	private String contributorsUrl;
	@JsonProperty("subscribers_url")
	private String subscribersUrl;
	@JsonProperty("subscription_url")
	private String subscriptionUrl;
	@JsonProperty("commits_url")
	private String commitsUrl;
	@JsonProperty("git_commits_url")
	private String gitCommitsUrl;
	@JsonProperty("comments_url")
	private String commentsUrl;
	@JsonProperty("issue_comment_url")
	private String issueCommentUrl;
	@JsonProperty("contents_url")
	private String contentsUrl;
	@JsonProperty("compare_url")
	private String compareUrl;
	@JsonProperty("merges_url")
	private String mergesUrl;
	@JsonProperty("archive_url")
	private String archiveUrl;
	@JsonProperty("downloads_url")
	private String downloadsUrl;
	@JsonProperty("issues_url")
	private String issuesUrl;
	@JsonProperty("pulls_url")
	private String pullsUrl;
	@JsonProperty("milestones_url")
	private String milestonesUrl;
	@JsonProperty("notifications_url")
	private String notificationsUrl;
	@JsonProperty("labels_url")
	private String labelsUrl;
	@JsonProperty("releases_url")
	private String releasesUrl;
	@JsonProperty("deployments_url")
	private String deploymentsUrl;
	@JsonProperty("created_at")
	private Object createdAt;
	@JsonProperty("updated_at")
	private Object updatedAt;
	@JsonProperty("pushed_at")
	private Object pushedAt;
	@JsonProperty("git_url")
	private String gitUrl;
	@JsonProperty("ssh_url")
	private String sshUrl;
	@JsonProperty("clone_url")
	private String cloneUrl;
	@JsonProperty("svn_url")
	private String svnUrl;
	@JsonProperty("homepage")
	private String homepage;
	@JsonProperty("size")
	private int size;
	@JsonProperty("stargazers_count")
	private int stargazersCount;
	@JsonProperty("watchers_count")
	private int watchersCount;
	@JsonProperty("language")
	private String language;
	@JsonProperty("has_issues")
	private boolean hasIssues;
	@JsonProperty("has_projects")
	private boolean hasProjects;
	@JsonProperty("hasdownloads")
	private boolean hasdownloads;
	@JsonProperty("has_wiki")
	private boolean hasWiki;
	@JsonProperty("has_pages")
	private boolean hasPages;
	@JsonProperty("forks_count")
	private int forksCount;
	@JsonProperty("mirror_url")
	private String mirrorUrl;
	@JsonProperty("archived")
	private boolean archived;
	@JsonProperty("disabled")
	private boolean disabled;
	@JsonProperty("open_issues_count")
	private int openIssuesCount;
	@JsonProperty("license")
	private String license;
	@JsonProperty("forks")
	private int forks;
	@JsonProperty("open_issues")
	private int openIssues;
	@JsonProperty("watchers")
	private int watchers;
	@JsonProperty("default_branch")
	private String defaultBranch;
	@JsonProperty("temp_clone_token")
	private String tempCloneToken;
	@JsonProperty("network_count")
	private int networkCount;
	@JsonProperty("subscribers_count")
	private String subscribersCount;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * No args constructor for use in serialization
	 *
	 */
	public Repo() {
	}

	/**
	 *
	 * @param sshUrl
	 * @param archiveUrl
	 * @param languagesUrl
	 * @param language
	 * @param assigneesUrl
	 * @param commitsUrl
	 * @param openIssues
	 * @param cloneUrl
	 * @param forksCount
	 * @param subscribersUrl
	 * @param createdAt
	 * @param forksUrl
	 * @param watchersCount
	 * @param _private
	 * @param issueCommentUrl
	 * @param statusesUrl
	 * @param id
	 * @param collaboratorsUrl
	 * @param hasdownloads
	 * @param updatedAt
	 * @param forks
	 * @param labelsUrl
	 * @param defaultBranch
	 * @param tempCloneToken
	 * @param subscribersCount
	 * @param keysUrl
	 * @param downloadsUrl
	 * @param contentsUrl
	 * @param pushedAt
	 * @param tagsUrl
	 * @param node
	 * @param license
	 * @param commentsUrl
	 * @param size
	 * @param treesUrl
	 * @param name
	 * @param mergesUrl
	 * @param teamsUrl
	 * @param blobsUrl
	 * @param issueEventsUrl
	 * @param hasPages
	 * @param milestonesUrl
	 * @param issuesUrl
	 * @param releasesUrl
	 * @param description
	 * @param watchers
	 * @param branchesUrl
	 * @param contributorsUrl
	 * @param networkCount
	 * @param gitRefsUrl
	 * @param hooksUrl
	 * @param openIssuesCount
	 * @param archived
	 * @param stargazersCount
	 * @param disabled
	 * @param hasIssues
	 * @param owner
	 * @param hasWiki
	 * @param compareUrl
	 * @param gitCommitsUrl
	 * @param htmlUrl
	 * @param stargazersUrl
	 * @param fullName
	 * @param svnUrl
	 * @param url
	 * @param pullsUrl
	 * @param mirrorUrl
	 * @param fork
	 * @param hasProjects
	 * @param deploymentsUrl
	 * @param eventsUrl
	 * @param gitTagsUrl
	 * @param notificationsUrl
	 * @param gitUrl
	 * @param subscriptionUrl
	 * @param homepage
	 */
	public Repo(String id, String node, String name, String fullName, boolean _private, Object owner, String htmlUrl,
			String description, boolean fork, String url, String forksUrl, String keysUrl, String collaboratorsUrl,
			String teamsUrl, String hooksUrl, String issueEventsUrl, String eventsUrl, String assigneesUrl,
			String branchesUrl, String tagsUrl, String blobsUrl, String gitTagsUrl, String gitRefsUrl, String treesUrl,
			String statusesUrl, String languagesUrl, String stargazersUrl, String contributorsUrl,
			String subscribersUrl, String subscriptionUrl, String commitsUrl, String gitCommitsUrl, String commentsUrl,
			String issueCommentUrl, String contentsUrl, String compareUrl, String mergesUrl, String archiveUrl,
			String downloadsUrl, String issuesUrl, String pullsUrl, String milestonesUrl, String notificationsUrl,
			String labelsUrl, String releasesUrl, String deploymentsUrl, Object createdAt, Object updatedAt,
			Object pushedAt, String gitUrl, String sshUrl, String cloneUrl, String svnUrl, String homepage, int size,
			int stargazersCount, int watchersCount, String language, boolean hasIssues, boolean hasProjects,
			boolean hasdownloads, boolean hasWiki, boolean hasPages, int forksCount, String mirrorUrl, boolean archived,
			boolean disabled, int openIssuesCount, String license, int forks, int openIssues, int watchers,
			String defaultBranch, String tempCloneToken, int networkCount, String subscribersCount) {
		super();
		this.id = id;
		this.node = node;
		this.name = name;
		this.fullName = fullName;
		this._private = _private;
		this.owner = owner;
		this.htmlUrl = htmlUrl;
		this.description = description;
		this.fork = fork;
		this.url = url;
		this.forksUrl = forksUrl;
		this.keysUrl = keysUrl;
		this.collaboratorsUrl = collaboratorsUrl;
		this.teamsUrl = teamsUrl;
		this.hooksUrl = hooksUrl;
		this.issueEventsUrl = issueEventsUrl;
		this.eventsUrl = eventsUrl;
		this.assigneesUrl = assigneesUrl;
		this.branchesUrl = branchesUrl;
		this.tagsUrl = tagsUrl;
		this.blobsUrl = blobsUrl;
		this.gitTagsUrl = gitTagsUrl;
		this.gitRefsUrl = gitRefsUrl;
		this.treesUrl = treesUrl;
		this.statusesUrl = statusesUrl;
		this.languagesUrl = languagesUrl;
		this.stargazersUrl = stargazersUrl;
		this.contributorsUrl = contributorsUrl;
		this.subscribersUrl = subscribersUrl;
		this.subscriptionUrl = subscriptionUrl;
		this.commitsUrl = commitsUrl;
		this.gitCommitsUrl = gitCommitsUrl;
		this.commentsUrl = commentsUrl;
		this.issueCommentUrl = issueCommentUrl;
		this.contentsUrl = contentsUrl;
		this.compareUrl = compareUrl;
		this.mergesUrl = mergesUrl;
		this.archiveUrl = archiveUrl;
		this.downloadsUrl = downloadsUrl;
		this.issuesUrl = issuesUrl;
		this.pullsUrl = pullsUrl;
		this.milestonesUrl = milestonesUrl;
		this.notificationsUrl = notificationsUrl;
		this.labelsUrl = labelsUrl;
		this.releasesUrl = releasesUrl;
		this.deploymentsUrl = deploymentsUrl;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.pushedAt = pushedAt;
		this.gitUrl = gitUrl;
		this.sshUrl = sshUrl;
		this.cloneUrl = cloneUrl;
		this.svnUrl = svnUrl;
		this.homepage = homepage;
		this.size = size;
		this.stargazersCount = stargazersCount;
		this.watchersCount = watchersCount;
		this.language = language;
		this.hasIssues = hasIssues;
		this.hasProjects = hasProjects;
		this.hasdownloads = hasdownloads;
		this.hasWiki = hasWiki;
		this.hasPages = hasPages;
		this.forksCount = forksCount;
		this.mirrorUrl = mirrorUrl;
		this.archived = archived;
		this.disabled = disabled;
		this.openIssuesCount = openIssuesCount;
		this.license = license;
		this.forks = forks;
		this.openIssues = openIssues;
		this.watchers = watchers;
		this.defaultBranch = defaultBranch;
		this.tempCloneToken = tempCloneToken;
		this.networkCount = networkCount;
		this.subscribersCount = subscribersCount;
	}

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("node")
	public String getNode() {
		return node;
	}

	@JsonProperty("node")
	public void setNode(String node) {
		this.node = node;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("full_name")
	public String getFullName() {
		return fullName;
	}

	@JsonProperty("full_name")
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@JsonProperty("private")
	public boolean isPrivate() {
		return _private;
	}

	@JsonProperty("private")
	public void setPrivate(boolean _private) {
		this._private = _private;
	}

	@JsonProperty("owner")
	public Object getOwner() {
		return owner;
	}

	@JsonProperty("owner")
	public void setOwner(Object owner) {
		this.owner = owner;
	}

	@JsonProperty("html_url")
	public String getHtmlUrl() {
		return htmlUrl;
	}

	@JsonProperty("html_url")
	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
	}

	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
	}

	@JsonProperty("fork")
	public boolean isFork() {
		return fork;
	}

	@JsonProperty("fork")
	public void setFork(boolean fork) {
		this.fork = fork;
	}

	@JsonProperty("url")
	public String getUrl() {
		return url;
	}

	@JsonProperty("url")
	public void setUrl(String url) {
		this.url = url;
	}

	@JsonProperty("forks_url")
	public String getForksUrl() {
		return forksUrl;
	}

	@JsonProperty("forks_url")
	public void setForksUrl(String forksUrl) {
		this.forksUrl = forksUrl;
	}

	@JsonProperty("keys_url")
	public String getKeysUrl() {
		return keysUrl;
	}

	@JsonProperty("keys_url")
	public void setKeysUrl(String keysUrl) {
		this.keysUrl = keysUrl;
	}

	@JsonProperty("collaborators_url")
	public String getCollaboratorsUrl() {
		return collaboratorsUrl;
	}

	@JsonProperty("collaborators_url")
	public void setCollaboratorsUrl(String collaboratorsUrl) {
		this.collaboratorsUrl = collaboratorsUrl;
	}

	@JsonProperty("teams_url")
	public String getTeamsUrl() {
		return teamsUrl;
	}

	@JsonProperty("teams_url")
	public void setTeamsUrl(String teamsUrl) {
		this.teamsUrl = teamsUrl;
	}

	@JsonProperty("hooks_url")
	public String getHooksUrl() {
		return hooksUrl;
	}

	@JsonProperty("hooks_url")
	public void setHooksUrl(String hooksUrl) {
		this.hooksUrl = hooksUrl;
	}

	@JsonProperty("issue_events_url")
	public String getIssueEventsUrl() {
		return issueEventsUrl;
	}

	@JsonProperty("issue_events_url")
	public void setIssueEventsUrl(String issueEventsUrl) {
		this.issueEventsUrl = issueEventsUrl;
	}

	@JsonProperty("events_url")
	public String getEventsUrl() {
		return eventsUrl;
	}

	@JsonProperty("events_url")
	public void setEventsUrl(String eventsUrl) {
		this.eventsUrl = eventsUrl;
	}

	@JsonProperty("assignees_url")
	public String getAssigneesUrl() {
		return assigneesUrl;
	}

	@JsonProperty("assignees_url")
	public void setAssigneesUrl(String assigneesUrl) {
		this.assigneesUrl = assigneesUrl;
	}

	@JsonProperty("branches_url")
	public String getBranchesUrl() {
		return branchesUrl;
	}

	@JsonProperty("branches_url")
	public void setBranchesUrl(String branchesUrl) {
		this.branchesUrl = branchesUrl;
	}

	@JsonProperty("tags_url")
	public String getTagsUrl() {
		return tagsUrl;
	}

	@JsonProperty("tags_url")
	public void setTagsUrl(String tagsUrl) {
		this.tagsUrl = tagsUrl;
	}

	@JsonProperty("blobs_url")
	public String getBlobsUrl() {
		return blobsUrl;
	}

	@JsonProperty("blobs_url")
	public void setBlobsUrl(String blobsUrl) {
		this.blobsUrl = blobsUrl;
	}

	@JsonProperty("git_tags_url")
	public String getGitTagsUrl() {
		return gitTagsUrl;
	}

	@JsonProperty("git_tags_url")
	public void setGitTagsUrl(String gitTagsUrl) {
		this.gitTagsUrl = gitTagsUrl;
	}

	@JsonProperty("git_refs_url")
	public String getGitRefsUrl() {
		return gitRefsUrl;
	}

	@JsonProperty("git_refs_url")
	public void setGitRefsUrl(String gitRefsUrl) {
		this.gitRefsUrl = gitRefsUrl;
	}

	@JsonProperty("trees_url")
	public String getTreesUrl() {
		return treesUrl;
	}

	@JsonProperty("trees_url")
	public void setTreesUrl(String treesUrl) {
		this.treesUrl = treesUrl;
	}

	@JsonProperty("statuses_url")
	public String getStatusesUrl() {
		return statusesUrl;
	}

	@JsonProperty("statuses_url")
	public void setStatusesUrl(String statusesUrl) {
		this.statusesUrl = statusesUrl;
	}

	@JsonProperty("languages_url")
	public String getLanguagesUrl() {
		return languagesUrl;
	}

	@JsonProperty("languages_url")
	public void setLanguagesUrl(String languagesUrl) {
		this.languagesUrl = languagesUrl;
	}

	@JsonProperty("stargazers_url")
	public String getStargazersUrl() {
		return stargazersUrl;
	}

	@JsonProperty("stargazers_url")
	public void setStargazersUrl(String stargazersUrl) {
		this.stargazersUrl = stargazersUrl;
	}

	@JsonProperty("contributors_url")
	public String getContributorsUrl() {
		return contributorsUrl;
	}

	@JsonProperty("contributors_url")
	public void setContributorsUrl(String contributorsUrl) {
		this.contributorsUrl = contributorsUrl;
	}

	@JsonProperty("subscribers_url")
	public String getSubscribersUrl() {
		return subscribersUrl;
	}

	@JsonProperty("subscribers_url")
	public void setSubscribersUrl(String subscribersUrl) {
		this.subscribersUrl = subscribersUrl;
	}

	@JsonProperty("subscription_url")
	public String getSubscriptionUrl() {
		return subscriptionUrl;
	}

	@JsonProperty("subscription_url")
	public void setSubscriptionUrl(String subscriptionUrl) {
		this.subscriptionUrl = subscriptionUrl;
	}

	@JsonProperty("commits_url")
	public String getCommitsUrl() {
		return commitsUrl;
	}

	@JsonProperty("commits_url")
	public void setCommitsUrl(String commitsUrl) {
		this.commitsUrl = commitsUrl;
	}

	@JsonProperty("git_commits_url")
	public String getGitCommitsUrl() {
		return gitCommitsUrl;
	}

	@JsonProperty("git_commits_url")
	public void setGitCommitsUrl(String gitCommitsUrl) {
		this.gitCommitsUrl = gitCommitsUrl;
	}

	@JsonProperty("comments_url")
	public String getCommentsUrl() {
		return commentsUrl;
	}

	@JsonProperty("comments_url")
	public void setCommentsUrl(String commentsUrl) {
		this.commentsUrl = commentsUrl;
	}

	@JsonProperty("issue_comment_url")
	public String getIssueCommentUrl() {
		return issueCommentUrl;
	}

	@JsonProperty("issue_comment_url")
	public void setIssueCommentUrl(String issueCommentUrl) {
		this.issueCommentUrl = issueCommentUrl;
	}

	@JsonProperty("contents_url")
	public String getContentsUrl() {
		return contentsUrl;
	}

	@JsonProperty("contents_url")
	public void setContentsUrl(String contentsUrl) {
		this.contentsUrl = contentsUrl;
	}

	@JsonProperty("compare_url")
	public String getCompareUrl() {
		return compareUrl;
	}

	@JsonProperty("compare_url")
	public void setCompareUrl(String compareUrl) {
		this.compareUrl = compareUrl;
	}

	@JsonProperty("merges_url")
	public String getMergesUrl() {
		return mergesUrl;
	}

	@JsonProperty("merges_url")
	public void setMergesUrl(String mergesUrl) {
		this.mergesUrl = mergesUrl;
	}

	@JsonProperty("archive_url")
	public String getArchiveUrl() {
		return archiveUrl;
	}

	@JsonProperty("archive_url")
	public void setArchiveUrl(String archiveUrl) {
		this.archiveUrl = archiveUrl;
	}

	@JsonProperty("downloads_url")
	public String getDownloadsUrl() {
		return downloadsUrl;
	}

	@JsonProperty("downloads_url")
	public void setDownloadsUrl(String downloadsUrl) {
		this.downloadsUrl = downloadsUrl;
	}

	@JsonProperty("issues_url")
	public String getIssuesUrl() {
		return issuesUrl;
	}

	@JsonProperty("issues_url")
	public void setIssuesUrl(String issuesUrl) {
		this.issuesUrl = issuesUrl;
	}

	@JsonProperty("pulls_url")
	public String getPullsUrl() {
		return pullsUrl;
	}

	@JsonProperty("pulls_url")
	public void setPullsUrl(String pullsUrl) {
		this.pullsUrl = pullsUrl;
	}

	@JsonProperty("milestones_url")
	public String getMilestonesUrl() {
		return milestonesUrl;
	}

	@JsonProperty("milestones_url")
	public void setMilestonesUrl(String milestonesUrl) {
		this.milestonesUrl = milestonesUrl;
	}

	@JsonProperty("notifications_url")
	public String getNotificationsUrl() {
		return notificationsUrl;
	}

	@JsonProperty("notifications_url")
	public void setNotificationsUrl(String notificationsUrl) {
		this.notificationsUrl = notificationsUrl;
	}

	@JsonProperty("labels_url")
	public String getLabelsUrl() {
		return labelsUrl;
	}

	@JsonProperty("labels_url")
	public void setLabelsUrl(String labelsUrl) {
		this.labelsUrl = labelsUrl;
	}

	@JsonProperty("releases_url")
	public String getReleasesUrl() {
		return releasesUrl;
	}

	@JsonProperty("releases_url")
	public void setReleasesUrl(String releasesUrl) {
		this.releasesUrl = releasesUrl;
	}

	@JsonProperty("deployments_url")
	public String getDeploymentsUrl() {
		return deploymentsUrl;
	}

	@JsonProperty("deployments_url")
	public void setDeploymentsUrl(String deploymentsUrl) {
		this.deploymentsUrl = deploymentsUrl;
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

	@JsonProperty("pushed_at")
	public Object getPushedAt() {
		return pushedAt;
	}

	@JsonProperty("pushed_at")
	public void setPushedAt(Object pushedAt) {
		this.pushedAt = pushedAt;
	}

	@JsonProperty("git_url")
	public String getGitUrl() {
		return gitUrl;
	}

	@JsonProperty("git_url")
	public void setGitUrl(String gitUrl) {
		this.gitUrl = gitUrl;
	}

	@JsonProperty("ssh_url")
	public String getSshUrl() {
		return sshUrl;
	}

	@JsonProperty("ssh_url")
	public void setSshUrl(String sshUrl) {
		this.sshUrl = sshUrl;
	}

	@JsonProperty("clone_url")
	public String getCloneUrl() {
		return cloneUrl;
	}

	@JsonProperty("clone_url")
	public void setCloneUrl(String cloneUrl) {
		this.cloneUrl = cloneUrl;
	}

	@JsonProperty("svn_url")
	public String getSvnUrl() {
		return svnUrl;
	}

	@JsonProperty("svn_url")
	public void setSvnUrl(String svnUrl) {
		this.svnUrl = svnUrl;
	}

	@JsonProperty("homepage")
	public String getHomepage() {
		return homepage;
	}

	@JsonProperty("homepage")
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	@JsonProperty("size")
	public int getSize() {
		return size;
	}

	@JsonProperty("size")
	public void setSize(int size) {
		this.size = size;
	}

	@JsonProperty("stargazers_count")
	public int getStargazersCount() {
		return stargazersCount;
	}

	@JsonProperty("stargazers_count")
	public void setStargazersCount(int stargazersCount) {
		this.stargazersCount = stargazersCount;
	}

	@JsonProperty("watchers_count")
	public int getWatchersCount() {
		return watchersCount;
	}

	@JsonProperty("watchers_count")
	public void setWatchersCount(int watchersCount) {
		this.watchersCount = watchersCount;
	}

	@JsonProperty("language")
	public String getLanguage() {
		return language;
	}

	@JsonProperty("language")
	public void setLanguage(String language) {
		this.language = language;
	}

	@JsonProperty("has_issues")
	public boolean isHasIssues() {
		return hasIssues;
	}

	@JsonProperty("has_issues")
	public void setHasIssues(boolean hasIssues) {
		this.hasIssues = hasIssues;
	}

	@JsonProperty("has_projects")
	public boolean isHasProjects() {
		return hasProjects;
	}

	@JsonProperty("has_projects")
	public void setHasProjects(boolean hasProjects) {
		this.hasProjects = hasProjects;
	}

	@JsonProperty("hasdownloads")
	public boolean isHasdownloads() {
		return hasdownloads;
	}

	@JsonProperty("hasdownloads")
	public void setHasdownloads(boolean hasdownloads) {
		this.hasdownloads = hasdownloads;
	}

	@JsonProperty("has_wiki")
	public boolean isHasWiki() {
		return hasWiki;
	}

	@JsonProperty("has_wiki")
	public void setHasWiki(boolean hasWiki) {
		this.hasWiki = hasWiki;
	}

	@JsonProperty("has_pages")
	public boolean isHasPages() {
		return hasPages;
	}

	@JsonProperty("has_pages")
	public void setHasPages(boolean hasPages) {
		this.hasPages = hasPages;
	}

	@JsonProperty("forks_count")
	public int getForksCount() {
		return forksCount;
	}

	@JsonProperty("forks_count")
	public void setForksCount(int forksCount) {
		this.forksCount = forksCount;
	}

	@JsonProperty("mirror_url")
	public String getMirrorUrl() {
		return mirrorUrl;
	}

	@JsonProperty("mirror_url")
	public void setMirrorUrl(String mirrorUrl) {
		this.mirrorUrl = mirrorUrl;
	}

	@JsonProperty("archived")
	public boolean isArchived() {
		return archived;
	}

	@JsonProperty("archived")
	public void setArchived(boolean archived) {
		this.archived = archived;
	}

	@JsonProperty("disabled")
	public boolean isDisabled() {
		return disabled;
	}

	@JsonProperty("disabled")
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	@JsonProperty("open_issues_count")
	public int getOpenIssuesCount() {
		return openIssuesCount;
	}

	@JsonProperty("open_issues_count")
	public void setOpenIssuesCount(int openIssuesCount) {
		this.openIssuesCount = openIssuesCount;
	}

	@JsonProperty("license")
	public String getLicense() {
		return license;
	}

	@JsonProperty("license")
	public void setLicense(String license) {
		this.license = license;
	}

	@JsonProperty("forks")
	public int getForks() {
		return forks;
	}

	@JsonProperty("forks")
	public void setForks(int forks) {
		this.forks = forks;
	}

	@JsonProperty("open_issues")
	public int getOpenIssues() {
		return openIssues;
	}

	@JsonProperty("open_issues")
	public void setOpenIssues(int openIssues) {
		this.openIssues = openIssues;
	}

	@JsonProperty("watchers")
	public int getWatchers() {
		return watchers;
	}

	@JsonProperty("watchers")
	public void setWatchers(int watchers) {
		this.watchers = watchers;
	}

	@JsonProperty("default_branch")
	public String getDefaultBranch() {
		return defaultBranch;
	}

	@JsonProperty("default_branch")
	public void setDefaultBranch(String defaultBranch) {
		this.defaultBranch = defaultBranch;
	}

	@JsonProperty("temp_clone_token")
	public String getTempCloneToken() {
		return tempCloneToken;
	}

	@JsonProperty("temp_clone_token")
	public void setTempCloneToken(String tempCloneToken) {
		this.tempCloneToken = tempCloneToken;
	}

	@JsonProperty("network_count")
	public int getNetworkCount() {
		return networkCount;
	}

	@JsonProperty("network_count")
	public void setNetworkCount(int networkCount) {
		this.networkCount = networkCount;
	}

	@JsonProperty("subscribers_count")
	public String getSubscribersCount() {
		return subscribersCount;
	}

	@JsonProperty("subscribers_count")
	public void setSubscribersCount(String subscribersCount) {
		this.subscribersCount = subscribersCount;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id).append("node", node).append("name", name)
				.append("fullName", fullName).append("_private", _private).append("owner", owner)
				.append("htmlUrl", htmlUrl).append("description", description).append("fork", fork).append("url", url)
				.append("forksUrl", forksUrl).append("keysUrl", keysUrl).append("collaboratorsUrl", collaboratorsUrl)
				.append("teamsUrl", teamsUrl).append("hooksUrl", hooksUrl).append("issueEventsUrl", issueEventsUrl)
				.append("eventsUrl", eventsUrl).append("assigneesUrl", assigneesUrl).append("branchesUrl", branchesUrl)
				.append("tagsUrl", tagsUrl).append("blobsUrl", blobsUrl).append("gitTagsUrl", gitTagsUrl)
				.append("gitRefsUrl", gitRefsUrl).append("treesUrl", treesUrl).append("statusesUrl", statusesUrl)
				.append("languagesUrl", languagesUrl).append("stargazersUrl", stargazersUrl)
				.append("contributorsUrl", contributorsUrl).append("subscribersUrl", subscribersUrl)
				.append("subscriptionUrl", subscriptionUrl).append("commitsUrl", commitsUrl)
				.append("gitCommitsUrl", gitCommitsUrl).append("commentsUrl", commentsUrl)
				.append("issueCommentUrl", issueCommentUrl).append("contentsUrl", contentsUrl)
				.append("compareUrl", compareUrl).append("mergesUrl", mergesUrl).append("archiveUrl", archiveUrl)
				.append("downloadsUrl", downloadsUrl).append("issuesUrl", issuesUrl).append("pullsUrl", pullsUrl)
				.append("milestonesUrl", milestonesUrl).append("notificationsUrl", notificationsUrl)
				.append("labelsUrl", labelsUrl).append("releasesUrl", releasesUrl)
				.append("deploymentsUrl", deploymentsUrl).append("createdAt", createdAt).append("updatedAt", updatedAt)
				.append("pushedAt", pushedAt).append("gitUrl", gitUrl).append("sshUrl", sshUrl)
				.append("cloneUrl", cloneUrl).append("svnUrl", svnUrl).append("homepage", homepage).append("size", size)
				.append("stargazersCount", stargazersCount).append("watchersCount", watchersCount)
				.append("language", language).append("hasIssues", hasIssues).append("hasProjects", hasProjects)
				.append("hasdownloads", hasdownloads).append("hasWiki", hasWiki).append("hasPages", hasPages)
				.append("forksCount", forksCount).append("mirrorUrl", mirrorUrl).append("archived", archived)
				.append("disabled", disabled).append("openIssuesCount", openIssuesCount).append("license", license)
				.append("forks", forks).append("openIssues", openIssues).append("watchers", watchers)
				.append("defaultBranch", defaultBranch).append("tempCloneToken", tempCloneToken)
				.append("networkCount", networkCount).append("subscribersCount", subscribersCount)
				.append("additionalProperties", additionalProperties).toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(sshUrl).append(archiveUrl).append(languagesUrl).append(language)
				.append(assigneesUrl).append(commitsUrl).append(openIssues).append(cloneUrl).append(forksCount)
				.append(subscribersUrl).append(createdAt).append(forksUrl).append(watchersCount).append(_private)
				.append(issueCommentUrl).append(statusesUrl).append(id).append(collaboratorsUrl).append(hasdownloads)
				.append(updatedAt).append(forks).append(labelsUrl).append(defaultBranch).append(tempCloneToken)
				.append(subscribersCount).append(keysUrl).append(downloadsUrl).append(contentsUrl).append(pushedAt)
				.append(tagsUrl).append(node).append(license).append(commentsUrl).append(size).append(treesUrl)
				.append(name).append(additionalProperties).append(mergesUrl).append(teamsUrl).append(blobsUrl)
				.append(issueEventsUrl).append(hasPages).append(milestonesUrl).append(issuesUrl).append(releasesUrl)
				.append(description).append(watchers).append(branchesUrl).append(contributorsUrl).append(networkCount)
				.append(gitRefsUrl).append(hooksUrl).append(openIssuesCount).append(archived).append(stargazersCount)
				.append(disabled).append(hasIssues).append(owner).append(hasWiki).append(compareUrl)
				.append(gitCommitsUrl).append(htmlUrl).append(stargazersUrl).append(fullName).append(svnUrl).append(url)
				.append(pullsUrl).append(mirrorUrl).append(fork).append(hasProjects).append(deploymentsUrl)
				.append(eventsUrl).append(gitTagsUrl).append(notificationsUrl).append(gitUrl).append(subscriptionUrl)
				.append(homepage).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Repo) == false) {
			return false;
		}
		Repo rhs = ((Repo) other);
		return new EqualsBuilder().append(sshUrl, rhs.sshUrl).append(archiveUrl, rhs.archiveUrl)
				.append(languagesUrl, rhs.languagesUrl).append(language, rhs.language)
				.append(assigneesUrl, rhs.assigneesUrl).append(commitsUrl, rhs.commitsUrl)
				.append(openIssues, rhs.openIssues).append(cloneUrl, rhs.cloneUrl).append(forksCount, rhs.forksCount)
				.append(subscribersUrl, rhs.subscribersUrl).append(createdAt, rhs.createdAt)
				.append(forksUrl, rhs.forksUrl).append(watchersCount, rhs.watchersCount).append(_private, rhs._private)
				.append(issueCommentUrl, rhs.issueCommentUrl).append(statusesUrl, rhs.statusesUrl).append(id, rhs.id)
				.append(collaboratorsUrl, rhs.collaboratorsUrl).append(hasdownloads, rhs.hasdownloads)
				.append(updatedAt, rhs.updatedAt).append(forks, rhs.forks).append(labelsUrl, rhs.labelsUrl)
				.append(defaultBranch, rhs.defaultBranch).append(tempCloneToken, rhs.tempCloneToken)
				.append(subscribersCount, rhs.subscribersCount).append(keysUrl, rhs.keysUrl)
				.append(downloadsUrl, rhs.downloadsUrl).append(contentsUrl, rhs.contentsUrl)
				.append(pushedAt, rhs.pushedAt).append(tagsUrl, rhs.tagsUrl).append(node, rhs.node)
				.append(license, rhs.license).append(commentsUrl, rhs.commentsUrl).append(size, rhs.size)
				.append(treesUrl, rhs.treesUrl).append(name, rhs.name)
				.append(additionalProperties, rhs.additionalProperties).append(mergesUrl, rhs.mergesUrl)
				.append(teamsUrl, rhs.teamsUrl).append(blobsUrl, rhs.blobsUrl)
				.append(issueEventsUrl, rhs.issueEventsUrl).append(hasPages, rhs.hasPages)
				.append(milestonesUrl, rhs.milestonesUrl).append(issuesUrl, rhs.issuesUrl)
				.append(releasesUrl, rhs.releasesUrl).append(description, rhs.description)
				.append(watchers, rhs.watchers).append(branchesUrl, rhs.branchesUrl)
				.append(contributorsUrl, rhs.contributorsUrl).append(networkCount, rhs.networkCount)
				.append(gitRefsUrl, rhs.gitRefsUrl).append(hooksUrl, rhs.hooksUrl)
				.append(openIssuesCount, rhs.openIssuesCount).append(archived, rhs.archived)
				.append(stargazersCount, rhs.stargazersCount).append(disabled, rhs.disabled)
				.append(hasIssues, rhs.hasIssues).append(owner, rhs.owner).append(hasWiki, rhs.hasWiki)
				.append(compareUrl, rhs.compareUrl).append(gitCommitsUrl, rhs.gitCommitsUrl)
				.append(htmlUrl, rhs.htmlUrl).append(stargazersUrl, rhs.stargazersUrl).append(fullName, rhs.fullName)
				.append(svnUrl, rhs.svnUrl).append(url, rhs.url).append(pullsUrl, rhs.pullsUrl)
				.append(mirrorUrl, rhs.mirrorUrl).append(fork, rhs.fork).append(hasProjects, rhs.hasProjects)
				.append(deploymentsUrl, rhs.deploymentsUrl).append(eventsUrl, rhs.eventsUrl)
				.append(gitTagsUrl, rhs.gitTagsUrl).append(notificationsUrl, rhs.notificationsUrl)
				.append(gitUrl, rhs.gitUrl).append(subscriptionUrl, rhs.subscriptionUrl).append(homepage, rhs.homepage)
				.isEquals();
	}

}