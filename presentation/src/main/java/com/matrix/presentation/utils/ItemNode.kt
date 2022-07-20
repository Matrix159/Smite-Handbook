package com.matrix.presentation.utils

import com.matrix.domain.models.Item


class ItemNode(var value: Item) {
  var parent: ItemNode? = null

  var children: MutableList<ItemNode> = mutableListOf()

  fun addChild(node: ItemNode) {
    children.add(node)
    node.parent = this
  }

  fun findChildren(itemsGroupedByTier: Map<Long, List<Item>>): ItemNode {
    val currentTier = this.value.itemTier

    if (itemsGroupedByTier.containsKey(currentTier + 1)) {
      itemsGroupedByTier[currentTier + 1]!!.filter { it.childItemID == this.value.itemID }.forEach {
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
   * @param item The item node you want to find
   * @return The found item node
   */
  fun findItem(item: Item): ItemNode? {
//    if (this.parent != null) {
//      throw UnsupportedOperationException()
//    }

    if (this.value.itemID == item.itemID) {
      return this
    } else {
      this.children.forEach {
        val itemNode = it.findItem(item)
        if (itemNode != null) {
          return itemNode
        }
      }
    }
    return null
  }

  fun totalCost(): Long {
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
}