select * from users
Join band_user ON band_user.user_id = users.user_id
Join band ON band.band_id = band_user.band_id;