/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://code.google.com/p/google-apis-client-generator/
 * (build: 2013-05-14 03:07:32 UTC)
 * on 2013-05-16 at 22:42:20 UTC 
 * Modify at your own risk.
 */

package com.example.teamup.deporteendpoint;

/**
 * Service definition for Deporteendpoint (v1).
 *
 * <p>
 * This is an API
 * </p>
 *
 * <p>
 * For more information about this service, see the
 * <a href="" target="_blank">API Documentation</a>
 * </p>
 *
 * <p>
 * This service uses {@link DeporteendpointRequestInitializer} to initialize global parameters via its
 * {@link Builder}.
 * </p>
 *
 * @since 1.3
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public class Deporteendpoint extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient {

  // Note: Leave this static initializer at the top of the file.
  static {
    com.google.api.client.util.Preconditions.checkState(
        com.google.api.client.googleapis.GoogleUtils.MAJOR_VERSION == 1 &&
        com.google.api.client.googleapis.GoogleUtils.MINOR_VERSION == 14,
        "You are currently running with version %s of google-api-client. " +
        "You need version 1.14 of google-api-client to run version " +
        "1.14.2-beta of the  library.", com.google.api.client.googleapis.GoogleUtils.VERSION);
  }

  /**
   * The default encoded root URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_ROOT_URL = "https://teamup-server.appspot.com/_ah/api/";

  /**
   * The default encoded service path of the service. This is determined when the library is
   * generated and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_SERVICE_PATH = "deporteendpoint/v1/";

  /**
   * The default encoded base URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   */
  public static final String DEFAULT_BASE_URL = DEFAULT_ROOT_URL + DEFAULT_SERVICE_PATH;

  /**
   * Constructor.
   *
   * <p>
   * Use {@link Builder} if you need to specify any of the optional parameters.
   * </p>
   *
   * @param transport HTTP transport, which should normally be:
   *        <ul>
   *        <li>Google App Engine:
   *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
   *        <li>Android: {@code newCompatibleTransport} from
   *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
   *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
   *        </li>
   *        </ul>
   * @param jsonFactory JSON factory, which may be:
   *        <ul>
   *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
   *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
   *        <li>Android Honeycomb or higher:
   *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
   *        </ul>
   * @param httpRequestInitializer HTTP request initializer or {@code null} for none
   * @since 1.7
   */
  public Deporteendpoint(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
      com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
    this(new Builder(transport, jsonFactory, httpRequestInitializer));
  }

  /**
   * @param builder builder
   */
  Deporteendpoint(Builder builder) {
    super(builder);
  }

  @Override
  protected void initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest<?> httpClientRequest) throws java.io.IOException {
    super.initialize(httpClientRequest);
  }

  /**
   * Create a request for the method "getDeporte".
   *
   * This request holds the parameters needed by the the deporteendpoint server.  After setting any
   * optional parameters, call the {@link GetDeporte#execute()} method to invoke the remote operation.
   *
   * @param id
   * @return the request
   */
  public GetDeporte getDeporte(java.lang.Long id) throws java.io.IOException {
    GetDeporte result = new GetDeporte(id);
    initialize(result);
    return result;
  }

  public class GetDeporte extends DeporteendpointRequest<com.example.teamup.deporteendpoint.model.Deporte> {

    private static final String REST_PATH = "deporte/{id}";

    /**
     * Create a request for the method "getDeporte".
     *
     * This request holds the parameters needed by the the deporteendpoint server.  After setting any
     * optional parameters, call the {@link GetDeporte#execute()} method to invoke the remote
     * operation. <p> {@link
     * GetDeporte#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param id
     * @since 1.13
     */
    protected GetDeporte(java.lang.Long id) {
      super(Deporteendpoint.this, "GET", REST_PATH, null, com.example.teamup.deporteendpoint.model.Deporte.class);
      this.id = com.google.api.client.util.Preconditions.checkNotNull(id, "Required parameter id must be specified.");
    }

    @Override
    public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
      return super.executeUsingHead();
    }

    @Override
    public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
      return super.buildHttpRequestUsingHead();
    }

    @Override
    public GetDeporte setAlt(java.lang.String alt) {
      return (GetDeporte) super.setAlt(alt);
    }

    @Override
    public GetDeporte setFields(java.lang.String fields) {
      return (GetDeporte) super.setFields(fields);
    }

    @Override
    public GetDeporte setKey(java.lang.String key) {
      return (GetDeporte) super.setKey(key);
    }

    @Override
    public GetDeporte setOauthToken(java.lang.String oauthToken) {
      return (GetDeporte) super.setOauthToken(oauthToken);
    }

    @Override
    public GetDeporte setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (GetDeporte) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public GetDeporte setQuotaUser(java.lang.String quotaUser) {
      return (GetDeporte) super.setQuotaUser(quotaUser);
    }

    @Override
    public GetDeporte setUserIp(java.lang.String userIp) {
      return (GetDeporte) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.Long id;

    /**

     */
    public java.lang.Long getId() {
      return id;
    }

    public GetDeporte setId(java.lang.Long id) {
      this.id = id;
      return this;
    }

    @Override
    public GetDeporte set(String parameterName, Object value) {
      return (GetDeporte) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "insertDeporte".
   *
   * This request holds the parameters needed by the the deporteendpoint server.  After setting any
   * optional parameters, call the {@link InsertDeporte#execute()} method to invoke the remote
   * operation.
   *
   * @param content the {@link com.example.teamup.deporteendpoint.model.Deporte}
   * @return the request
   */
  public InsertDeporte insertDeporte(com.example.teamup.deporteendpoint.model.Deporte content) throws java.io.IOException {
    InsertDeporte result = new InsertDeporte(content);
    initialize(result);
    return result;
  }

  public class InsertDeporte extends DeporteendpointRequest<com.example.teamup.deporteendpoint.model.Deporte> {

    private static final String REST_PATH = "deporte";

    /**
     * Create a request for the method "insertDeporte".
     *
     * This request holds the parameters needed by the the deporteendpoint server.  After setting any
     * optional parameters, call the {@link InsertDeporte#execute()} method to invoke the remote
     * operation. <p> {@link InsertDeporte#initialize(com.google.api.client.googleapis.services.Abstra
     * ctGoogleClientRequest)} must be called to initialize this instance immediately after invoking
     * the constructor. </p>
     *
     * @param content the {@link com.example.teamup.deporteendpoint.model.Deporte}
     * @since 1.13
     */
    protected InsertDeporte(com.example.teamup.deporteendpoint.model.Deporte content) {
      super(Deporteendpoint.this, "POST", REST_PATH, content, com.example.teamup.deporteendpoint.model.Deporte.class);
    }

    @Override
    public InsertDeporte setAlt(java.lang.String alt) {
      return (InsertDeporte) super.setAlt(alt);
    }

    @Override
    public InsertDeporte setFields(java.lang.String fields) {
      return (InsertDeporte) super.setFields(fields);
    }

    @Override
    public InsertDeporte setKey(java.lang.String key) {
      return (InsertDeporte) super.setKey(key);
    }

    @Override
    public InsertDeporte setOauthToken(java.lang.String oauthToken) {
      return (InsertDeporte) super.setOauthToken(oauthToken);
    }

    @Override
    public InsertDeporte setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (InsertDeporte) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public InsertDeporte setQuotaUser(java.lang.String quotaUser) {
      return (InsertDeporte) super.setQuotaUser(quotaUser);
    }

    @Override
    public InsertDeporte setUserIp(java.lang.String userIp) {
      return (InsertDeporte) super.setUserIp(userIp);
    }

    @Override
    public InsertDeporte set(String parameterName, Object value) {
      return (InsertDeporte) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "listDeporte".
   *
   * This request holds the parameters needed by the the deporteendpoint server.  After setting any
   * optional parameters, call the {@link ListDeporte#execute()} method to invoke the remote
   * operation.
   *
   * @return the request
   */
  public ListDeporte listDeporte() throws java.io.IOException {
    ListDeporte result = new ListDeporte();
    initialize(result);
    return result;
  }

  public class ListDeporte extends DeporteendpointRequest<com.example.teamup.deporteendpoint.model.CollectionResponseDeporte> {

    private static final String REST_PATH = "deporte";

    /**
     * Create a request for the method "listDeporte".
     *
     * This request holds the parameters needed by the the deporteendpoint server.  After setting any
     * optional parameters, call the {@link ListDeporte#execute()} method to invoke the remote
     * operation. <p> {@link
     * ListDeporte#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @since 1.13
     */
    protected ListDeporte() {
      super(Deporteendpoint.this, "GET", REST_PATH, null, com.example.teamup.deporteendpoint.model.CollectionResponseDeporte.class);
    }

    @Override
    public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
      return super.executeUsingHead();
    }

    @Override
    public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
      return super.buildHttpRequestUsingHead();
    }

    @Override
    public ListDeporte setAlt(java.lang.String alt) {
      return (ListDeporte) super.setAlt(alt);
    }

    @Override
    public ListDeporte setFields(java.lang.String fields) {
      return (ListDeporte) super.setFields(fields);
    }

    @Override
    public ListDeporte setKey(java.lang.String key) {
      return (ListDeporte) super.setKey(key);
    }

    @Override
    public ListDeporte setOauthToken(java.lang.String oauthToken) {
      return (ListDeporte) super.setOauthToken(oauthToken);
    }

    @Override
    public ListDeporte setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (ListDeporte) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public ListDeporte setQuotaUser(java.lang.String quotaUser) {
      return (ListDeporte) super.setQuotaUser(quotaUser);
    }

    @Override
    public ListDeporte setUserIp(java.lang.String userIp) {
      return (ListDeporte) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.String cursor;

    /**

     */
    public java.lang.String getCursor() {
      return cursor;
    }

    public ListDeporte setCursor(java.lang.String cursor) {
      this.cursor = cursor;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.Integer limit;

    /**

     */
    public java.lang.Integer getLimit() {
      return limit;
    }

    public ListDeporte setLimit(java.lang.Integer limit) {
      this.limit = limit;
      return this;
    }

    @Override
    public ListDeporte set(String parameterName, Object value) {
      return (ListDeporte) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "removeDeporte".
   *
   * This request holds the parameters needed by the the deporteendpoint server.  After setting any
   * optional parameters, call the {@link RemoveDeporte#execute()} method to invoke the remote
   * operation.
   *
   * @param id
   * @return the request
   */
  public RemoveDeporte removeDeporte(java.lang.Long id) throws java.io.IOException {
    RemoveDeporte result = new RemoveDeporte(id);
    initialize(result);
    return result;
  }

  public class RemoveDeporte extends DeporteendpointRequest<com.example.teamup.deporteendpoint.model.Deporte> {

    private static final String REST_PATH = "deporte/{id}";

    /**
     * Create a request for the method "removeDeporte".
     *
     * This request holds the parameters needed by the the deporteendpoint server.  After setting any
     * optional parameters, call the {@link RemoveDeporte#execute()} method to invoke the remote
     * operation. <p> {@link RemoveDeporte#initialize(com.google.api.client.googleapis.services.Abstra
     * ctGoogleClientRequest)} must be called to initialize this instance immediately after invoking
     * the constructor. </p>
     *
     * @param id
     * @since 1.13
     */
    protected RemoveDeporte(java.lang.Long id) {
      super(Deporteendpoint.this, "DELETE", REST_PATH, null, com.example.teamup.deporteendpoint.model.Deporte.class);
      this.id = com.google.api.client.util.Preconditions.checkNotNull(id, "Required parameter id must be specified.");
    }

    @Override
    public RemoveDeporte setAlt(java.lang.String alt) {
      return (RemoveDeporte) super.setAlt(alt);
    }

    @Override
    public RemoveDeporte setFields(java.lang.String fields) {
      return (RemoveDeporte) super.setFields(fields);
    }

    @Override
    public RemoveDeporte setKey(java.lang.String key) {
      return (RemoveDeporte) super.setKey(key);
    }

    @Override
    public RemoveDeporte setOauthToken(java.lang.String oauthToken) {
      return (RemoveDeporte) super.setOauthToken(oauthToken);
    }

    @Override
    public RemoveDeporte setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (RemoveDeporte) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public RemoveDeporte setQuotaUser(java.lang.String quotaUser) {
      return (RemoveDeporte) super.setQuotaUser(quotaUser);
    }

    @Override
    public RemoveDeporte setUserIp(java.lang.String userIp) {
      return (RemoveDeporte) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.Long id;

    /**

     */
    public java.lang.Long getId() {
      return id;
    }

    public RemoveDeporte setId(java.lang.Long id) {
      this.id = id;
      return this;
    }

    @Override
    public RemoveDeporte set(String parameterName, Object value) {
      return (RemoveDeporte) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "updateDeporte".
   *
   * This request holds the parameters needed by the the deporteendpoint server.  After setting any
   * optional parameters, call the {@link UpdateDeporte#execute()} method to invoke the remote
   * operation.
   *
   * @param content the {@link com.example.teamup.deporteendpoint.model.Deporte}
   * @return the request
   */
  public UpdateDeporte updateDeporte(com.example.teamup.deporteendpoint.model.Deporte content) throws java.io.IOException {
    UpdateDeporte result = new UpdateDeporte(content);
    initialize(result);
    return result;
  }

  public class UpdateDeporte extends DeporteendpointRequest<com.example.teamup.deporteendpoint.model.Deporte> {

    private static final String REST_PATH = "deporte";

    /**
     * Create a request for the method "updateDeporte".
     *
     * This request holds the parameters needed by the the deporteendpoint server.  After setting any
     * optional parameters, call the {@link UpdateDeporte#execute()} method to invoke the remote
     * operation. <p> {@link UpdateDeporte#initialize(com.google.api.client.googleapis.services.Abstra
     * ctGoogleClientRequest)} must be called to initialize this instance immediately after invoking
     * the constructor. </p>
     *
     * @param content the {@link com.example.teamup.deporteendpoint.model.Deporte}
     * @since 1.13
     */
    protected UpdateDeporte(com.example.teamup.deporteendpoint.model.Deporte content) {
      super(Deporteendpoint.this, "PUT", REST_PATH, content, com.example.teamup.deporteendpoint.model.Deporte.class);
    }

    @Override
    public UpdateDeporte setAlt(java.lang.String alt) {
      return (UpdateDeporte) super.setAlt(alt);
    }

    @Override
    public UpdateDeporte setFields(java.lang.String fields) {
      return (UpdateDeporte) super.setFields(fields);
    }

    @Override
    public UpdateDeporte setKey(java.lang.String key) {
      return (UpdateDeporte) super.setKey(key);
    }

    @Override
    public UpdateDeporte setOauthToken(java.lang.String oauthToken) {
      return (UpdateDeporte) super.setOauthToken(oauthToken);
    }

    @Override
    public UpdateDeporte setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (UpdateDeporte) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public UpdateDeporte setQuotaUser(java.lang.String quotaUser) {
      return (UpdateDeporte) super.setQuotaUser(quotaUser);
    }

    @Override
    public UpdateDeporte setUserIp(java.lang.String userIp) {
      return (UpdateDeporte) super.setUserIp(userIp);
    }

    @Override
    public UpdateDeporte set(String parameterName, Object value) {
      return (UpdateDeporte) super.set(parameterName, value);
    }
  }

  /**
   * Builder for {@link Deporteendpoint}.
   *
   * <p>
   * Implementation is not thread-safe.
   * </p>
   *
   * @since 1.3.0
   */
  public static final class Builder extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient.Builder {

    /**
     * Returns an instance of a new builder.
     *
     * @param transport HTTP transport, which should normally be:
     *        <ul>
     *        <li>Google App Engine:
     *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
     *        <li>Android: {@code newCompatibleTransport} from
     *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
     *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
     *        </li>
     *        </ul>
     * @param jsonFactory JSON factory, which may be:
     *        <ul>
     *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
     *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
     *        <li>Android Honeycomb or higher:
     *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
     *        </ul>
     * @param httpRequestInitializer HTTP request initializer or {@code null} for none
     * @since 1.7
     */
    public Builder(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
        com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      super(
          transport,
          jsonFactory,
          DEFAULT_ROOT_URL,
          DEFAULT_SERVICE_PATH,
          httpRequestInitializer,
          false);
    }

    /** Builds a new instance of {@link Deporteendpoint}. */
    @Override
    public Deporteendpoint build() {
      return new Deporteendpoint(this);
    }

    @Override
    public Builder setRootUrl(String rootUrl) {
      return (Builder) super.setRootUrl(rootUrl);
    }

    @Override
    public Builder setServicePath(String servicePath) {
      return (Builder) super.setServicePath(servicePath);
    }

    @Override
    public Builder setHttpRequestInitializer(com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      return (Builder) super.setHttpRequestInitializer(httpRequestInitializer);
    }

    @Override
    public Builder setApplicationName(String applicationName) {
      return (Builder) super.setApplicationName(applicationName);
    }

    @Override
    public Builder setSuppressPatternChecks(boolean suppressPatternChecks) {
      return (Builder) super.setSuppressPatternChecks(suppressPatternChecks);
    }

    @Override
    public Builder setSuppressRequiredParameterChecks(boolean suppressRequiredParameterChecks) {
      return (Builder) super.setSuppressRequiredParameterChecks(suppressRequiredParameterChecks);
    }

    @Override
    public Builder setSuppressAllChecks(boolean suppressAllChecks) {
      return (Builder) super.setSuppressAllChecks(suppressAllChecks);
    }

    /**
     * Set the {@link DeporteendpointRequestInitializer}.
     *
     * @since 1.12
     */
    public Builder setDeporteendpointRequestInitializer(
        DeporteendpointRequestInitializer deporteendpointRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(deporteendpointRequestInitializer);
    }

    @Override
    public Builder setGoogleClientRequestInitializer(
        com.google.api.client.googleapis.services.GoogleClientRequestInitializer googleClientRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
    }
  }
}
