package io.shiftleft.semanticcpg.language.types.expressions

import gremlin.scala.GremlinScala
import io.shiftleft.codepropertygraph.generated.{EdgeTypes, NodeKeys, nodes}
import io.shiftleft.semanticcpg.language._

object ControlStructure {
  val secondChildIndex = Int.box(2)
  val thirdChildIndex = Int.box(3)
}

class ControlStructure(val wrapped: NodeSteps[nodes.ControlStructure]) extends AnyVal {
  import ControlStructure._
  private def raw: GremlinScala[nodes.ControlStructure] = wrapped.raw

  /**
    * The expression introduced by this control structure, if any
    * */
  def condition: NodeSteps[nodes.Expression] =
    new NodeSteps(raw.out(EdgeTypes.CONDITION).cast[nodes.Expression])

  def whenTrue: NodeSteps[nodes.AstNode] =
    new NodeSteps(raw.out.has(NodeKeys.ORDER, secondChildIndex).cast[nodes.AstNode])

  def whenFalse: NodeSteps[nodes.AstNode] =
    new NodeSteps(raw.out.has(NodeKeys.ORDER, thirdChildIndex).cast[nodes.AstNode])

}
