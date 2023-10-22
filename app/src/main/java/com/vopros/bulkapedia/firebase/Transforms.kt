package com.vopros.bulkapedia.firebase

import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import com.vopros.bulkapedia.category.Category
import com.vopros.bulkapedia.category.CategoryDTO
import com.vopros.bulkapedia.comment.Comment
import com.vopros.bulkapedia.comment.CommentDTO
import com.vopros.bulkapedia.gears.Gear
import com.vopros.bulkapedia.gears.GearCell
import com.vopros.bulkapedia.gears.GearDTO
import com.vopros.bulkapedia.gears.GearSet
import com.vopros.bulkapedia.hero.Hero
import com.vopros.bulkapedia.hero.HeroDTO
import com.vopros.bulkapedia.hero.HeroType
import com.vopros.bulkapedia.map.GameMap
import com.vopros.bulkapedia.map.GameMapDTO
import com.vopros.bulkapedia.user.User
import com.vopros.bulkapedia.user.UserDTO
import com.vopros.bulkapedia.userSet.UserSet
import com.vopros.bulkapedia.userSet.UserSetDTO

/**
 * @param dto class with var params
 *
 * **/
inline fun <reified T, D> toObject(dto: Class<D>, doc: DocumentSnapshot, toPojo: (D) -> T?): T? {
    return toPojo(doc.toObject(dto)!!)
}

fun toHero(doc: DocumentSnapshot): Hero? = toObject(HeroDTO::class.java, doc) {
    Log.d("toHero", "$it")
    Hero(it.id, it.active, it.difficult, it.image, HeroType.valueOf(it.type), it.counterpicks, it.stats, it.personalGears)
}

fun toCategory(doc: DocumentSnapshot): Category? = toObject(CategoryDTO::class.java, doc) {
    Category(it.id, it.title, it.subTitle, it.enabled, it.icon, it.destination)
}

fun toGameMap(doc: DocumentSnapshot): GameMap? = toObject(GameMapDTO::class.java, doc) {
    GameMap(it.id, it.image, it.spawns, it.mode)
}

fun toUserSet(doc: DocumentSnapshot): UserSet? = toObject(UserSetDTO::class.java, doc) {
    UserSet(it.documentId, it.author, it.gears.mapKeys { (k, _) -> GearCell.valueOf(k.uppercase()) }, it.hero, it.liked)
}

fun toUser(doc: DocumentSnapshot): User? = toObject(UserDTO::class.java, doc) {
    User(it.id, it.admin, it.email, it.nickname, it.password, it.updateEmail, it.updateNickname)
}

fun toComment(doc: DocumentSnapshot): Comment? = toObject(CommentDTO::class.java, doc) {
    Comment(it.id, it.author, it.set, it.text, it.date)
}

fun toGear(doc: DocumentSnapshot): Gear? = toObject(GearDTO::class.java, doc) {
    Gear(it.id, GearCell.valueOf(it.gearCell.uppercase()), GearSet.valueOf(it.gearSet), it.icon)
}