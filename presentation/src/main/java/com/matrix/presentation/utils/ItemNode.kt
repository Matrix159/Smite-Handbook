package com.matrix.presentation.utils

import com.matrix.shared.data.model.items.ItemInformation


class ItemNode(var value: ItemInformation) {
  var parent: ItemNode? = null

  var children: MutableList<ItemNode> = mutableListOf()

  fun addChild(node: ItemNode) {
    children.add(node)
    node.parent = this
  }

  fun findChildren(itemsGroupedByTier: Map<Long, List<ItemInformation>>): ItemNode {
    val currentTier = this.value.itemTier

    if (itemsGroupedByTier.containsKey((currentTier + 1).toLong())) {
      itemsGroupedByTier[(currentTier + 1).toLong()]!!
        .filter { it.childItemID == this.value.itemID }
        .forEach {
          addChild(ItemNode(it).findChildren(itemsGroupedByTier))
        }
    }
    return this
  }

//  /**
//   * Should be called on the root node of the tree
//   */
//  fun itemExistsInThisTree(item: Item): Boolean {
//    if (this.parent != null) {
//      throw UnsupportedOperationException()
//    }
//    var itemFound = false
//
//
//    return itemFound
//  }

  /**
   * Call on the root node of the tree, this finds the item within the tree if it exits and returns it.
   * @param itemInformation The item node you want to find
   * @return The found item node
   */
  fun findItem(itemInformation: ItemInformation): ItemNode? {
//    if (this.parent != null) {
//      throw UnsupportedOperationException()
//    }

    if (this.value.itemID == itemInformation.itemID) {
      return this
    } else {
      this.children.forEach {
        val itemNode = it.findItem(itemInformation)
        if (itemNode != null) {
          return itemNode
        }
      }
    }
    return null
  }

  fun totalCost(): Int {
    var currentNode: ItemNode? = this
    var cost = value.price

    while (currentNode?.parent != null) {
      cost += currentNode.parent!!.value.price
      currentNode = currentNode.parent
    }
    return cost
  }

  override fun toString(): String {
    return this.value.deviceName + "\n" + this.children.joinToString(", ")
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as ItemNode

    if (value != other.value) return false
    if (parent != other.parent) return false
    return children == other.children
  }

  override fun hashCode(): Int {
    var result = value.hashCode()
    result = 31 * result + (parent?.hashCode() ?: 0)
    result = 31 * result + children.hashCode()
    return result
  }
}