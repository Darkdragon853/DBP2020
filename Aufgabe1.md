# Aufgabe 1 -> Überführung UML zu Relationenmodell

Primärschlüssel sind **fett**, Fremdschlüssel *kursiv*.

Abfahrt!

Tag(**id**,name);

TagClass(**id**, name);

Continent(**id**, name);

Country(**id**, name, *continent_id*);

City(**id**, name, *country_id*);

Person(**id**, firstName, lastName, gender, birthday, email, speaks, browserUsed, locationIP, *city_id*);

Company(**id**, name, *country_id*);

University(**id**, name, *city_id*);

Forum(**id**, title, creationDate, *moderator*);

Post(**id**, language, imageFile, creationDate, browserUsed, locationIP, content, length, *forum_id*, *author_id*, *country_id*);

Comment(**id**, creationDate, browserUsed, locationIP, content, length, *author_id*, *country_id*, *reply_to_post_id*, *reply_to_comment_id*);

Forum_hasMember_Person(*person_id*, *forum_id*, joinDate);

Forum_hasTag_Tag(*forum_id*, *tag_id*);

Tag_hasType_TagClass(*tag_id*, *tagClass_id*);

TagClass_isSubclassOf_TagClass(tag_parent_id, *tag_child_id*);

Post_hasTag_Tag(*post_id*, *tag_id*);

Comment_hasTag_Tag(*comment_id*, *tag_id*);

Person_knows_Person(*person_1_id*, *person_2_id*, creationDate);

Person_studyAt_University(*person_id*, *university_id*, classYear);

Person_workAt_Company(*person_id*, *company_id*, workFrom);

Person_likes_Post(*person_id*, *post_id*, creationDate);

Person_likes_Comment(*person_id*, *comment_id*, creationDate);

Person_hasInterest_Tag(*person_id*, *tag_id*);
