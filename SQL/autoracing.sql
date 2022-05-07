CREATE TABLE countries(
    country_id SERIAL PRIMARY KEY NOT NULL,
    country_name VARCHAR(60) NOT NULL,
    country_abbr VARCHAR(5) NOT NULL
);

CREATE TABLE locations(
    location_id SERIAL PRIMARY KEY NOT NULL,
    location_name VARCHAR(50) NOT NULL,
    location_country INTEGER NOT NULL,
    FOREIGN KEY (location_country) REFERENCES countries (country_id)
);

CREATE TABLE companies (
    company_id SERIAL PRIMARY KEY NOT NULL,
    company_name VARCHAR(50) NOT NULL,
    company_location SERIAL NOT NULL,
    FOREIGN KEY (company_location) REFERENCES locations(location_id)
);

CREATE TABLE fuels (
    fuel_id SERIAL PRIMARY KEY NOT NULL,
    fuel_name VARCHAR(50) NOT NULL,
    fuel_manufacturer SERIAL NOT NULL,
    FOREIGN KEY (fuel_manufacturer) REFERENCES companies(company_id)
);

CREATE TABLE tyres (
    tyres_id SERIAL PRIMARY KEY NOT NULL,
    tyres_name VARCHAR(50) NOT NULL,
    tyres_manufacturer SERIAL NOT NULL,
    FOREIGN KEY (tyres_manufacturer) REFERENCES companies(company_id)
);

CREATE TABLE engines (
    engine_id SERIAL PRIMARY KEY NOT NULL,
    engine_name VARCHAR(50) NOT NULL,
    engine_manufacturer SERIAL NOT NULL,
    FOREIGN KEY (engine_manufacturer) REFERENCES companies(company_id)
);

CREATE TABLE chassis(
    chassis_id SERIAL PRIMARY KEY NOT NULL,
    chassis_name VARCHAR(50) NOT NULL,
    chassis_manufacturer SERIAL NOT NULL,
    FOREIGN KEY (chassis_manufacturer) REFERENCES companies(company_id)
);

CREATE TABLE cars (
    car_id SERIAL PRIMARY KEY NOT NULL,
    car_chassis SERIAL NOT NULL,
    car_engine SERIAL NOT NULL,
    car_tyres SERIAL NOT NULL,
    car_fuel SERIAL NOT NULL,
    FOREIGN KEY (car_chassis) REFERENCES chassis(chassis_id),
    FOREIGN KEY (car_engine) REFERENCES engines(engine_id),
    FOREIGN KEY (car_tyres) REFERENCES tyres (tyres_id),
    FOREIGN KEY (car_fuel) REFERENCES fuels(fuel_id)
);

CREATE TABLE tracks (
    track_id SERIAL PRIMARY KEY NOT NULL,
    track_name VARCHAR(50) NOT NULL,
    track_location SERIAL NOT NULL,
    FOREIGN KEY (track_location) REFERENCES locations (location_id)
);

CREATE TABLE teams (
    team_id SERIAL UNIQUE NOT NULL,
    team_name VARCHAR(50) NOT NULL,
    team_home_track SERIAL NOT NULL,
    team_since DATE NOT NULL,
    team_till DATE NOT NULL,
	PRIMARY KEY (team_id, team_since, team_till),
    FOREIGN KEY (team_home_track) REFERENCES tracks (track_id),
    CHECK(team_since<team_till)
);

CREATE TABLE persons(
    person_id SERIAL PRIMARY KEY NOT NULL,
    person_name VARCHAR(50) NOT NULL,
    person_other_name VARCHAR(50) NOT NULL,
    person_surname VARCHAR(50) NOT NULL,
    person_suffix VARCHAR(6) NOT NULL,
    person_sex VARCHAR(5) NOT NULL,
    person_birthday TIMESTAMPTZ NOT NULL,
    person_nationality SERIAL NOT NULL,
    FOREIGN KEY (person_nationality) REFERENCES countries (country_id)
);

CREATE TABLE scoring_systems (
    score_system_id SERIAL PRIMARY KEY NOT NULL,
    score_system_info VARCHAR(100) NOT NULL,
    score_lead_points SMALLINT NOT NULL
);

CREATE TABLE championships (
    champ_id SERIAL UNIQUE NOT NULL,
    champ_name VARCHAR(50) NOT NULL,
    champ_score_system SERIAL NOT NULL,
    champ_since TIMESTAMPTZ NOT NULL,
    champ_till TIMESTAMPTZ NOT NULL,
	PRIMARY KEY (champ_id, champ_since, champ_till),
    FOREIGN KEY (champ_score_system) REFERENCES scoring_systems(score_system_id),
    CHECK(champ_since<champ_till)
);

CREATE TABLE car_names(
    car_names_car_id SERIAL PRIMARY KEY NOT NULL,
    car_names_car_name VARCHAR(20) NOT NULL
);

CREATE TABLE entries (
    entries_champ_id SERIAL NOT NULL,
    entries_car_number SMALLINT NOT NULL,
    entries_driver_id SERIAL NOT NULL,
    entries_team_id SERIAL NOT NULL,
    entries_car_id SERIAL NOT NULL,
    entries_car_name SERIAL NOT NULL,
    entries_since DATE NOT NULL, 
    entries_till DATE NOT NULL,
	PRIMARY KEY (entries_champ_id, entries_car_number, entries_since, entries_till),
    FOREIGN KEY (entries_champ_id) REFERENCES championships(champ_id),
    FOREIGN KEY (entries_driver_id) REFERENCES persons (person_id),
    FOREIGN KEY (entries_team_id) REFERENCES teams(team_id),
    FOREIGN KEY (entries_car_id) REFERENCES cars(car_id),
    FOREIGN KEY (entries_car_name) REFERENCES car_names(car_names_car_id),
    CHECK(entries_since<entries_till),
    CHECK(entries_car_number>0)
);

CREATE TABLE practice_types (
    practice_type_id SERIAL PRIMARY KEY NOT NULL,
    practice_type_description VARCHAR(30) NOT NULL
);

CREATE TABLE lap_info (
    lap_info_track_id SERIAL UNIQUE NOT NULL,
    lap_info_lap_length FLOAT NOT NULL,
    lap_info_description VARCHAR(100) NOT NULL,
    lap_info_since TIMESTAMPTZ NOT NULL,
    lap_info_till TIMESTAMPTZ NOT NULL,
	PRIMARY KEY (lap_info_track_id, lap_info_since, lap_info_till),
    FOREIGN KEY (lap_info_track_id) REFERENCES tracks (track_id),
    CHECK(lap_info_since<lap_info_till),
    CHECK(lap_info_lap_length>0)
);

CREATE TABLE race_names (
    race_name_id SERIAL PRIMARY KEY NOT NULL,
    race_name VARCHAR(30) NOT NULL
);

CREATE TABLE events (
    event_id SERIAL PRIMARY KEY NOT NULL,
    event_race_name INTEGER NOT NULL,
    event_track_id INTEGER NOT NULL,
    event_champ_id INTEGER NOT NULL,
    event_race_date TIMESTAMPTZ NOT NULL,
    event_num_of_lap SMALLINT NOT NULL,
    FOREIGN KEY (event_race_name) REFERENCES race_names(race_name_id), 
    FOREIGN KEY (event_track_id) REFERENCES tracks(track_id),
    FOREIGN KEY (event_champ_id) REFERENCES championships(champ_id)
);

CREATE TABLE qualif_place_poins (
    qpp_score_system_id SERIAL UNIQUE NOT NULL,
    qpp_place SMALLINT NOT NULL,
    qpp_points SMALLINT NOT NULL,
	PRIMARY KEY (qpp_score_system_id, qpp_place),
    FOREIGN KEY (qpp_score_system_id) REFERENCES scoring_systems(score_system_id),
    CHECK(qpp_place>0)
);

CREATE TABLE race_place_points (
    rpp_score_system_id SERIAL UNIQUE NOT NULL,
    rpp_place SMALLINT NOT NULL,
    rpp_points SMALLINT NOT NULL,
	PRIMARY KEY (rpp_score_system_id, rpp_place),
    FOREIGN KEY (rpp_score_system_id) REFERENCES scoring_systems(score_system_id),
    CHECK (rpp_place>0)
);

CREATE TABLE practice_info (
    practice_info_id SERIAL PRIMARY KEY NOT NULL,
    practice_info_name VARCHAR(30) NOT NULL,
    practice_info_type_id INTEGER NOT NULL,
    pi_days_before_race SMALLINT NOT NULL,
    FOREIGN KEY (practice_info_type_id) REFERENCES practice_types(practice_type_id)
);

CREATE TABLE race_status (
    status_id SERIAL PRIMARY KEY NOT NULL,
    small_race_description VARCHAR(7) NOT NULL,
    full_race_description VARCHAR(100) NOT NULL
);

CREATE TABLE practice (
    p_event_id SERIAL UNIQUE NOT NULL, 
    p_practice_info_id SERIAL NOT NULL,
    p_car_number SMALLINT NOT NULL, 
    p_place SMALLINT NOT NULL,
    p_lap_time TIME NOT NULL,
    p_best_lap_completed DATE NOT NULL,
    p_all_used_laps SMALLINT NOT NULL,
    p_fast_laps_used SMALLINT NOT NULL,
    p_status_id INTEGER NOT NULL,
	PRIMARY KEY (p_event_id, p_practice_info_id, p_car_number),
    FOREIGN KEY (p_event_id) REFERENCES events(event_id),
    FOREIGN KEY (p_practice_info_id) REFERENCES practice_info(practice_info_id),
    FOREIGN KEY (p_status_id) REFERENCES race_status(status_id),
    CHECK (p_place>0),
    CHECK (p_car_number>0),
    CHECK (p_fast_laps_used<p_all_used_laps)
);

CREATE TABLE race (
    race_event_id SERIAL UNIQUE NOT NULL,
    race_car_number SMALLINT NOT NULL,
    race_place SMALLINT NOT NULL,
    race_time TIME NOT NULL,
    race_laps_completed SMALLINT NOT NULL,
    race_laps_leading SMALLINT NOT NULL,
    race_num_leading SMALLINT NOT NULL,
    race_leading_till TIME NOT NULL,
    race_best_lap SMALLINT NOT NULL,
    race_best_lap_time TIME NOT NULL,
    race_best_lap_completed DATE NOT NULL,
    race_pitlane_visited SMALLINT NOT NULL,
    race_start_id INTEGER NOT NULL,
    race_classified_id INTEGER NOT NULL,
    race_disqualification_id INTEGER NOT NULL,
	PRIMARY KEY (race_event_id, race_car_number),
    FOREIGN KEY (race_event_id) REFERENCES events(event_id),
    FOREIGN KEY (race_start_id) REFERENCES race_status (status_id),
    FOREIGN KEY (race_classified_id) REFERENCES race_status (status_id),
    FOREIGN KEY (race_disqualification_id) REFERENCES race_status (status_id),
    CHECK (race_laps_leading<=race_laps_completed),
    CHECK ((race_best_lap<=race_lap_completed) OR (race_best_lap IS NULL)),
    CHECK ((race_leading_till<=race_time) OR (race_leading_till IS NULL)),
    CHECK ((race_best_lap_completed<=race_time) OR  (race_best_lap_completed IS NULL)),
    CHECK (race_car_number>0)
);