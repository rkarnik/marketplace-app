CREATE TABLE IF NOT EXISTS user (
  id         INTEGER PRIMARY KEY,
  uuid VARCHAR(256) NOT NULL,
  first_name VARCHAR(256)  NOT NULL ,
  last_name VARCHAR(256)  NOT NULL ,
  company_name VARCHAR(256) ,
  created_on TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  updated_on TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

CREATE TABLE IF NOT EXISTS project (
  id         INTEGER PRIMARY KEY,
  uuid VARCHAR(256) NOT NULL,
  user_uuid VARCHAR(256),
  name VARCHAR(256)NOT NULL,
  description VARCHAR(256)NOT NULL,
  project_bid_status VARCHAR(256)NOT NULL,
  minimum_bid_value  DOUBLE,
  max_budget INTEGER NOT NULL,
  deadline_time TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  CONSTRAINT fk_user FOREIGN KEY (user_uuid)
  REFERENCES user (uuid),
  created_on TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  updated_on TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

CREATE TABLE IF NOT EXISTS bid (
  id         INTEGER PRIMARY KEY,
  uuid VARCHAR(256) NOT NULL,
  user_uuid VARCHAR(256),
  project_uuid VARCHAR(256),
  bid_value  DOUBLE NOT NULL,
  CONSTRAINT fk_user FOREIGN KEY (user_uuid)
  REFERENCES user (uuid),
  CONSTRAINT fk_project FOREIGN KEY (project_uuid)
  REFERENCES project (uuid),
  created_on TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  updated_on TIMESTAMP WITHOUT TIME ZONE NOT NULL
);